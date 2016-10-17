import java.rmi.Remote;
import java.rmi.RemoteException;

import java.io.FileNotFoundException;

public interface PlaceInterface extends Remote {
	public PlaceInfo findPlace (String city, String state) throws RemoteException, FileNotFoundException;
}
