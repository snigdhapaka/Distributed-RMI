import java.rmi.Naming;
import java.rmi.RemoteException;
// import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import PlaceData.PlaceDataProto.Place;
import PlaceData.PlaceDataProto.PlaceList;

import java.io.FileInputStream;

public class PlaceServer {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("usage: java SampleServer rmi_port");
            System.exit(1);
        }
        // Create and install a security manager
/*
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());
*/
        try {
            // first command-line argument is the port of the rmiregistry
            int port = Integer.parseInt(args[0]);
            String url = "//localhost:" + port + "/Place";
            System.out.println("binding " + url);
            Naming.rebind(url, new Place());
            // Naming.rebind("Sample", new Sample());
            System.out.println("server " + url + " is running...");
            PlaceList list = PlaceList.parseFrom(new FileInputStream("places-proto.bin"));
            Print(list);

        }
        catch (Exception e) {
            System.out.println("Sample server failed:" + e.getMessage());
        }
    }

    public static void Print(PlaceList list){
        for(Place p : list.getPlaceList()){
            System.out.println("state: "+p.getState()+" Place name: "+p.getName()+" lat: "+p.getLat()+" lon: "+p.getLon());
        }
    }  

}
