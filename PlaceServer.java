import java.rmi.Naming;
import java.rmi.RemoteException;
// import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;

import java.io.FileInputStream;
import PlaceData.PlaceDataProto.PlaceList;
import PlaceData.PlaceDataProto.Place;
import java.util.ArrayList;
import java.util.Iterator;

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
            // first command-line argument is the port of the rmiregistry
            int port = Integer.parseInt(args[0]);
            String url = "//localhost:" + port + "/Places";
            System.out.println("binding " + url);
            Naming.rebind(url, new Places());
            System.out.println("server " + url + " is running...");
            //createPlaceList();
        }
        catch (Exception e) {
            System.out.println("Sample server failed:" + e.getMessage());
        }
        
    }
    /*
    private static void createPlaceList () {
        ArrayList<PlaceInfo> places = new ArrayList<PlaceInfo>();
        try {
            Place place;
            PlaceList placeList = PlaceList.parseFrom(new FileInputStream("places-proto.bin"));
            for (Place a : placeList.getPlaceList()) {
                places.add(new PlaceInfo(a.getName(), a.getState(), a.getLat(), a.getLon()));
                //System.out.println("state: " + a.getState() + ", name: " + a.getName() + " lat: " + a.getLat() + " lon: " + a.getLon());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<PlaceInfo> placeIterator = places.iterator();
        while(placeIterator.hasNext()) {
            placeIterator.next().print();
        }
    }
    */
}
