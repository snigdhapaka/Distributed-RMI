import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlaceInterface extends Remote {
	public PlaceInfo findPlace(String city, String state) throws RemoteException;
}
