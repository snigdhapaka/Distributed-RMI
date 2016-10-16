import java.io.Serializable;

public class PlaceInfo implements Serializable {
	private String city;
	private String state;
	private double lat;
	private double lon;

	public PlaceInfo (String city, String state, double lat, double lon) {
		this.city = city;
		this.state = state;
		this.lat = lat;
		this.lon = lon;
	}	
	
	public String getCity () {
		return city;
	}

	public String getState () {
		return state;
	}

	public double getLat () {
		return lat;
	}
	public double getLon () {
		return lon;
	}

	public void print () {
		System.out.println(city + ", " + state + ": " + lat + ", " + lon);
	}
}