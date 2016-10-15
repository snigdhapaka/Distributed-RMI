import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import AirportData.AirportDataProto.Airport;
import AirportData.AirportDataProto.AirportList;

import java.io.FileInputStream;

public class AirportServer {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("usage: java AirportServer rmi_port");
            System.exit(1);
        }
        // Create and install a security manager
/*
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());
*/
        try {
            // first command-line argument is the port of the rmiregistry
            int port = Integer.parseInt(args[0]);
            String url = "//localhost:" + port + "/Sample";
            System.out.println("binding " + url);
            Naming.rebind(url, new Sample());
            // Naming.rebind("Sample", new Sample());
            System.out.println("server " + url + " is running...");
            AirportList list = AirportList.parseFrom(new FileInputStream("airports-proto.bin"));
            Print(list);

        }
        catch (Exception e) {
            System.out.println("Sample server failed:" + e.getMessage());
        }
    }

    public static void Print(AirportList list){
        for(Airport a : list.getAirportList()){
            System.out.println("state: "+a.getState()+" Place name: "+a.getName()+" code: "+a.getCode()+" lat: "+a.getLat()+" lon: "+a.getLon());
        }
    }
}
