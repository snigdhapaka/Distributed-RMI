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

            int port = 1099;
            try{
                port = Integer.parseInt(args[1]);
            } catch(NumberFormatException e){
                System.err.println("usage: java Client -h rmiregistryport -p port city state...");
                System.out.println("Please enter a number for the port\n");
                System.exit(1);
            }

            PlaceInfo place = null;

            try{
                // get places
                String placesUrl = "//" + args[0] + ":" + port + "/Places";

                PlaceInterface places = (PlaceInterface)Naming.lookup(placesUrl);
                place = places.findPlace(args[2], args[3]);

            } catch (FileNotFoundException e) {
                System.out.println("Server Exception: places-proto.bin not found in Places");
                System.exit(1);
            }

            if(place == null){
                System.out.println("Place not found");
            } else {

	            // get closest airports
                try{
    	            String airportsUrl = "//" + args[0] + ":" + port + "/Airports";
    	            AirportInterface airport = (AirportInterface)Naming.lookup(airportsUrl);
    	            ArrayList<AirportDistance> near = airport.findAirports(place.getLat(), place.getLon());
    	            
                    place.print();
                    for(AirportDistance nearest : near) {
                        nearest.print();
                    }
                    
                } catch (FileNotFoundException e) {
                    System.out.println("Server Exception: airports-proto.bin not found in Airports");
                    System.exit(1);
                }
       		}
        } catch(Exception e) {
            System.out.println("There was a problem connecting to the servers");
        } 
    }
}
