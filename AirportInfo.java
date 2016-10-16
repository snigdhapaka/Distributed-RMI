public class AirportInfo {
	private String state;
	private String name;
	private String code;	
	private double lat;
	private double lon;

	public AirportInfo (String state, String name, String code, double lat, double lon) {
		this.state = state;
		this.name = name;
		this.code = code;
		this.lat = lat;
		this.lon = lon;
	}	

	public void print () {
		System.out.println("state:" + state + ", name:" + name + ", code:" + code + ", lat:" + lat + ", lon:" + lon);
	}

	public String getState () {
		return state;
	}

	public String getName () {
		return name;
	}

	public String getCode () {
		return code;
	}

	public double getLat () {
		return lat;
	}

	public double getLon () {
		return lon;
	}
}