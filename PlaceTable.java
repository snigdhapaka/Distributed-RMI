import java.util.ArrayList;

public class PlaceTable{

	ArrayList<PlaceInfo> list;

	public PlaceTable(){
		list = new ArrayList<PlaceInfo>();
	}

	public void insert(String name, String state, double lat, double lon){
		PlaceInfo x = new PlaceInfo(name, state, lat, lon);
		list.add(x);
	}

	public PlaceInfo findPlace(String city, String state){
		Object[] places = list.toArray();
		String[] findcity = city.split(" ");
		//System.out.println("city: "+city+"  state: "+state);
		for(int i = 0; i < places.length; i++){
			PlaceInfo p = (PlaceInfo) places[i];
			//System.out.println("state: "+p.state);
			if(p.state.equalsIgnoreCase(state)){
				String[] x = p.name.split(" ");
				//System.out.println("found: "+x[0]);
				if(findcity[0].equalsIgnoreCase(x[0])){
					//System.out.println("found: "+x[0]);
					return p;
				}
			}
		}
		//System.out.println("the city I looked for: "+findcity[0]);
		return null;
	}

	public void printTable(){
		Object[] places = list.toArray();
		for(int i = 0; i < places.length; i++){
			PlaceInfo p = (PlaceInfo) places[i];
			System.out.println("state: "+p.state+" Place name: "+p.name+" lat: "+p.lat+" lon: "+p.lon);
		}
	}

}
