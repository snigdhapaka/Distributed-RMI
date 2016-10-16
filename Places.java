import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;
import PlaceData.PlaceDataProto.Place;
import PlaceData.PlaceDataProto.PlaceList;
import java.io.FileInputStream;

// this is the class with remote methods

public class Places
  extends UnicastRemoteObject
  implements PlaceInterface {

    private int a;
    private PlaceTable places;


    public Places() throws RemoteException {
	   System.out.println("New instance of Place created");
	   a = 1;

       places = new PlaceTable();
       try{
            PlaceList list = PlaceList.parseFrom(new FileInputStream("places-proto.bin"));
            for(Place p : list.getPlaceList()){
                places.insert(p.getName(), p.getState(), p.getLat(), p.getLon());
                //System.out.println("state: "+p.getState()+" Place name: "+p.getName()+" lat: "+p.getLat()+" lon: "+p.getLon());
            }
            places.printTable();

        }
       catch (Exception e) {
            System.out.println("Reading places-proto.bin file failed:" + e.getMessage());
        }
    }

    public PlaceInfo findPlace(String city, String state) throws RemoteException {
        PlaceInfo placeinfo = places.findPlace(city, state);
        return placeinfo; 
    }
}
