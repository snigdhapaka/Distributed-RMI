import java.rmi.Naming;
import java.rmi.RemoteException;
// import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;

import java.io.FileInputStream;

public class PlaceServer {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("usage: java PlaceServer rmi_port");
            System.exit(1);
        }
        // Create and install a security manager
/*
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());
*/
        try {
            int port = Integer.parseInt(args[0]);
            String url = "//localhost:" + port + "/Places";
            System.out.println("binding " + url);
            Naming.rebind(url, new Places());
            System.out.println("server " + url + " is running...");

        }
        catch (Exception e) {
            System.out.println("Sample server failed:" + e.getMessage());
        }
    }

}
