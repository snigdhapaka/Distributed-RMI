import java.rmi.Naming;
import java.rmi.RemoteException;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Client  {
    public static void main(String args[]) {
        try {
            if (args.length != 4) {
                System.err.println("usage: java Client -h rmiregistryport -p port city state... \n");
                System.exit(1);
            }

            // get places
            int port = Integer.parseInt(args[1]);
            String placesUrl = "//" + args[0] + ":" + port + "/Places";

            PlaceInterface places = (PlaceInterface)Naming.lookup(placesUrl);
            PlaceInfo place = places.findPlace(args[2], args[3]);

            if(place == null){
                System.out.println("Place not found");
            } else {
                place.print();

	            // get closest airports
	            String airportsUrl = "//" + args[0] + ":" + port + "/Airports";
	            AirportInterface airport = (AirportInterface)Naming.lookup(airportsUrl);
	            ArrayList<AirportDistance> near = airport.findAirports(place.getLat(), place.getLon());
	            for(AirportDistance nearest : near) {
	            	nearest.print();
	            }
       		}

        } catch (FileNotFoundException e) {
            System.out.println("Server Exception: File not found");
        } catch(Exception e) {
            System.out.println("Client exception: " + e);
        } 
    }
}
