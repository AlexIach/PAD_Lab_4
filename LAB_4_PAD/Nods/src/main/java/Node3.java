import Discovery.DiscoveryListener;
import Transport.TransportListener;
import work.Employee;
import work.Location;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Node3 {
    public static void main(String[] args) {
        int dataServerPort = 1236;
        Location loc1 =  new Location( new InetSocketAddress("127.0.0.1", 1234),1);
        if (args.length > 0) {
            dataServerPort = Integer.parseInt(args[0]);
        }

        Location serverLocation = new Location(new InetSocketAddress("127.0.0.1", dataServerPort),1);
        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Node 3 is running... on " + dataServerPort);

        new DiscoveryListener(serverLocation)
                .start();

        TransportListener transportListener = new TransportListener(dataServerPort,new ArrayList<Employee>() {{

            add(new Employee("Chack", "Noris", "Management", 501.0));
        }});
        transportListener.start();

        try {
            Thread.sleep(SECONDS.toMillis(200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        transportListener.setStopped(true);

    }
}
