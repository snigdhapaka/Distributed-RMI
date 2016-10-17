import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;

import PlaceData.PlaceDataProto.Place;
import PlaceData.PlaceDataProto.PlaceList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// this is the class with remote methods

public class Places extends UnicastRemoteObject implements PlaceInterface {

    private ArrayList<PlaceInfo> places;

    public Places () throws RemoteException {
    	places = new ArrayList<PlaceInfo>();
    	try {
    		PlaceList list = PlaceList.parseFrom(new FileInputStream("places-proto.bin"));
            for(Place p : list.getPlaceList()){
            	places.add(new PlaceInfo(p.getName(), p.getState(), p.getLat(), p.getLon()));
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	System.out.println("New instance of Places created");
    }

    public PlaceInfo findPlace (String city, String state) throws RemoteException, FileNotFoundException {
    	if (places.size() == 0) {
            throw new FileNotFoundException();
        }
        String [] cityName = city.split(" ");
    	for (PlaceInfo place : places) {
    		String [] placeName = place.getCity().split(" ");
    		if (cityName.length <= placeName.length) {
    			int len = cityName.length;
    			boolean found = true;
	    		for (int i = 0; i < len; i++) {
	    			if (!cityName[i].equalsIgnoreCase(placeName[i])) {
	    				found = false;
	    				break;
	    			}
	    		}
	    		if (found && (place.getState()).equalsIgnoreCase(state)) {
	    			return place;
	    		}
    		}
    	}
    	return null;
    }

}