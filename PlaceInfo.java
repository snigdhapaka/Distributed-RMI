import java.io.Serializable;

public class PlaceInfo implements Serializable{
	
	String name;
	String state;
	double lat;
	double lon;

	public PlaceInfo(String name, String state, double lat, double lon){
		this.name = name;
		this.state = state;
		this.lat = lat;
		this.lon = lon; 
	}

	public String toString(){
		return "Name: " + name + " state: " + state + " latitude: " + lat + " longitude: " + lon;
	}

}