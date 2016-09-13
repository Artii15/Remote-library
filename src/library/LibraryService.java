package library;

import library.books.Book;
import library.books.Catalog;
import library.books.CatalogPosition;
import library.books.Copy;
import library.exceptions.AlreadyOrderedException;
import library.exceptions.NoSuchCopyException;
import library.exceptions.NoSuchReaderException;
import library.reader.*;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

class LibraryService implements Library {
    private HashMap<Integer, Reader> readers = new HashMap<>();
    private Catalog catalog = new Catalog();
    private Borrows borrows = new Borrows(catalog);
    private Orders orders = new Orders(readers, catalog, borrows);
    private final Object readersLock = new Object();

    @Override
    public int create(Book book) throws RemoteException {
        return catalog.insert(book);
    }

    @Override
    public int create(Copy copy) throws RemoteException, NoSuchElementException {
        if(catalog.containsBook(copy.bookId)) {
            return catalog.insert(copy);
        }
        else {
            throw new NoSuchElementException("Book with id " + copy.bookId + " doesn't exist");
        }
    }

    @Override
    public List<CatalogPosition> findBooksByTitle(String title) throws RemoteException {
        return catalog.searchByTitle(title);
    }

    @Override
    public int register(Reader reader) throws RemoteException {
        synchronized (readersLock) {
            reader.id = ++Reader.highestId;
            readers.put(reader.id, reader);
        }
        return reader.id;
    }

    @Override
    public void order(int readerId, int signature, library.OrderNotification orderNotification) throws RemoteException, AlreadyOrderedException, NoSuchCopyException, NoSuchReaderException {
    	orders.addOrder(readerId, signature, orderNotification);
    }

    @Override
    public void returnOrderedCopy(int readerId, int signature) throws RemoteException, NoSuchReaderException, NoSuchCopyException {
    	orders.returnOrderedCopy(readerId, signature);
    }

	@Override
	public List<Reader> findReadersByName(String name) throws RemoteException {
		String searchedNamePattern = String.format(".*%s.*", normalizeStringPattern(name));
		return readers.values().stream().filter(reader -> {
			String fullName = String.format("%s %s", reader.firstName, reader.lastName);
			return normalizeStringPattern(fullName).matches(searchedNamePattern);
		}).collect(Collectors.toList());
	}
	
	private String normalizeStringPattern(String pattern) {
		return pattern.trim().toLowerCase();
	}

	@Override
	public List<Borrow> getReaderBorrows(int readerId) throws RemoteException, NoSuchReaderException {
		if(readers.containsKey(readerId)) {
			return borrows.getReaderBorrows(readerId);
		}
		else {
			throw new NoSuchReaderException();
		}
	}

	@Override
	public List<Borrow> getCopyBorrows(int signature) throws RemoteException, NoSuchCopyException {
		if(catalog.containsCopy(signature)) {
			return borrows.getCopyBorrows(signature);
		}
		else {
			throw new NoSuchCopyException();
		}
	}

	@Override
	public Reader getReader(int readerId) throws RemoteException, NoSuchReaderException {
		if(readers.containsKey(readerId)) {
			return readers.get(readerId);
		}
		else {
			throw new NoSuchReaderException();
		}
	}

	@Override
	public Copy getCopy(int signature) throws RemoteException, NoSuchCopyException {
		if(catalog.containsCopy(signature)) {
			return catalog.getCopy(signature);
		}
		else {
			throw new NoSuchCopyException();
		}
	}
}
