import java.io.Serializable;

public class AirportDistance implements Serializable {
	private String code;
	private String name;
	private String state;
	private double lat;
	private double lon;
	private double distance;

	public AirportDistance (String code, String name, String state, double lat, double lon, double distance) {
		this.code = code;
		this.name = name;
		this.state = state;
		this.lat = lat;
		this.lon = lon;
		this.distance = distance;
	}

	public AirportDistance (AirportInfo airport, double distance) {
		code = airport.getCode();
		name = airport.getName();
		state = airport.getState();
		lat = airport.getLat();
		lon = airport.getLon();
		this.distance = distance;
	}


	public double getDistance () {
		return distance;
	}

	public void print () {
        System.out.println("code=" + code + ", name=" + name + ", state=" + state + " distance: " + distance + " miles");
	}

}