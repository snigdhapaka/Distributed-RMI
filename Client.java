import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client  {
    public static void main(String args[]) {
        try {
            if (args.length < 2 || args.length > 4) {
                System.err.println("usage: java SampleClient [registry_host] [registry_port] City State \n");
                System.exit(1);
            }
            String host = "localhost";
            int port = 1099;
            String city, state;
            if(args.length == 2){
                city = args[0];
                state = args[1];
            } else if(args.length == 4){
                host = args[0];
                port = Integer.parseInt(args[1]);//what if this isn't and int?
                city = args[2];
                state = args[3];
            } else {
                try{
                    port = Integer.parseInt(args[0]);
                } catch(NumberFormatException e){
                    host = args[0];
                }
                city = args[1];
                    state = args[2];
            }

            String url = "//" + host + ":" + port + "/Places";
            System.out.println("looking up " + url);
            PlaceInterface place = (PlaceInterface)Naming.lookup(url);
            
            //System.out.println(place.findPlace(city, state));
            PlaceInfo p = place.findPlace(city, state);
            if(p == null){
                System.out.println("Place not found");
            } else {
                System.out.println(p);
            }
            

        } catch(Exception e) {
            System.out.println("Client exception: " + e);
        }
    }
}
