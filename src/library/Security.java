package library;

public class Security {
    public static void ensureSecurityManager() {
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }
}
