import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

public interface AirportInterface extends Remote {
	public ArrayList<AirportDistance> findAirports (double latitude, double longitude) throws RemoteException, FileNotFoundException;
}
