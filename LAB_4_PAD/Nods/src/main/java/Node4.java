import Discovery.DiscoveryListener;
import Transport.TransportListener;
import work.Employee;
import work.Location;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Node4 {
    public static void main(String[] args) {
        int dataServerPort = 1237;
        if (args.length > 0) {
            dataServerPort = Integer.parseInt(args[0]);
        }

        Location serverLocation = new Location( new InetSocketAddress("127.0.0.1", dataServerPort),1);
        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Node 4 is running... on " + dataServerPort);

        new DiscoveryListener(serverLocation)
                .start();

        TransportListener transportListener = new TransportListener(dataServerPort,new ArrayList<Employee>() {{
            add(new Employee("Mark", "Winsent", "Marketing", 500.0));
        }});
        transportListener.start();

        try {
            Thread.sleep(SECONDS.toMillis(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        transportListener.setStopped(true);

    }
}
