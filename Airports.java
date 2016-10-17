import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;

import AirportData.AirportDataProto.AirportList;
import AirportData.AirportDataProto.Airport;
import java.io.FileNotFoundException;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// this is the class with remote methods

public class Airports extends UnicastRemoteObject implements AirportInterface {
    private ArrayList<AirportInfo> airports;

    public Airports () throws RemoteException {
       airports = new ArrayList<AirportInfo>();
        try {
            Airport airport;
            AirportList airportList = AirportList.parseFrom(new FileInputStream("airports-proto.bin"));
            for (Airport a : airportList.getAirportList()) {
                airports.add(new AirportInfo(a.getState(), a.getName(), a.getCode(), a.getLat(), a.getLon()));
            }
        } catch (Exception e) {
            System.out.println("Missing airports-proto.bin file: " + e);
        }

        System.out.println("New instance of Airports created");
    }

    public ArrayList<AirportDistance> findAirports (double latitude, double longitude) throws RemoteException, FileNotFoundException {
        
        if(airports.size() == 0){
            throw new FileNotFoundException();
        }

        ArrayList<AirportDistance> closest = new ArrayList<AirportDistance>();

        double lat1 = Math.toRadians(latitude);
        double lon1 = Math.toRadians(longitude);
        ArrayList<AirportDistance> distances = new ArrayList<AirportDistance>();
            
        // get distance from city to every airport
        for (AirportInfo airport : airports) {
            double lon2 = Math.toRadians(airport.getLon());
            double lat2 = Math.toRadians(airport.getLat());

            double d = 1.1507794*60*Math.toDegrees(Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1)));
            AirportDistance distance = new AirportDistance(airport, d);
            distances.add(distance);
        }

            // sort shortest distance to longest
        Collections.sort(distances, new Comparator<AirportDistance>() {    
            @Override
            public int compare(AirportDistance o1, AirportDistance o2) {
                if (o1.getDistance() < o2.getDistance()) return -1;
                if (o1.getDistance() > o2.getDistance()) return 1;            
                return 0;
            }
        });

        // place first 5 in new ArrayList
        for (int i = 0; i < 5; i++) {
            closest.add(distances.get(i));
        }
        
        return closest;
    }


}
