import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.ArrayList;

public interface AirportInterface extends Remote {
	public ArrayList<AirportDistance> findAirports (double latitude, double longitude) throws RemoteException;
}
