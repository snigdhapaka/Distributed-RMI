import java.rmi.Naming;
import java.rmi.RemoteException;

import java.util.ArrayList;

public class Client  {
    public static void main(String args[]) {
        try {
            if (args.length != 4) {
                System.err.println("usage: java Client -h rmiregistryport -p port city state... \n");
                System.exit(1);
            }

            // get places
            int port = 1099;
            try{
                port = Integer.parseInt(args[1]);
            } catch(NumberFormatException e){
                System.err.println("usage: java Client -h rmiregistryport -p port city state...");
                System.out.println("Please enter a number for the port\n");
                System.exit(1);
            }
            String placesUrl = "//" + args[0] + ":" + port + "/Places";
            PlaceInterface places = (PlaceInterface)Naming.lookup(placesUrl);

            String airportsUrl = "//" + args[0] + ":" + port + "/Airports";
            AirportInterface airport = (AirportInterface)Naming.lookup(airportsUrl);
            
            PlaceInfo place = places.findPlace(args[2], args[3]);
            
            if(place == null){
                System.out.println("There was a problem with the Place server. No data in places file or it wasn't found");
            } else {
                if(place.getCity() == null && place.getState() == null){
                    System.out.println("Place not found");
                } else {
    	            // get closest airports
    	            ArrayList<AirportDistance> near = airport.findAirports(place.getLat(), place.getLon());
                    if(!near.isEmpty()){
                        place.print();
        	            for(AirportDistance nearest : near) {
        	            	nearest.print();
        	            }
                    } else {
                        System.out.println("There was a problem with the Airport server, no data was returned");
                    }
                }
       		}

        } catch(Exception e) {
            System.out.println("There was a problem connecting to the servers");
        }
    }
}
