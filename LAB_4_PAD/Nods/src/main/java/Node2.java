import Discovery.DiscoveryListener;
import Transport.TransportClient;
import Transport.TransportListener;
import work.Employee;
import work.Location;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Node2 {
    public static void main(String[] args) {
        int dataServerPort = 1235;
        Location loc1 =  new Location( new InetSocketAddress("127.0.0.1", 1238),1);
        Location loc2 =  new Location( new InetSocketAddress("127.0.0.1", 1239),1);

        ArrayList<Employee> employees = new ArrayList<Employee>() {{
            add(new Employee("Michkle", "Cain", "IT", 505.0));
            add(new Employee("Mag", "Rain", "IT", 504.0));
            add(new Employee("Jeremy", "QuChampark", "IT", 502.0));
            add(new Employee("Chavi", "Chase", "IT", 505.0));
            add(new Employee("Carter", "Shown", "IT", 501.0));
            add(new Employee("Stiven", "Tailer", "Management", 500.0));

        }};
        if (args.length > 0) {
            dataServerPort = Integer.parseInt(args[0]);
        }

        Location serverLocation = new Location(new InetSocketAddress("127.0.0.1", dataServerPort),2);
        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Node 2 is running... on " + dataServerPort);

        new DiscoveryListener(serverLocation)
                .start();


        try {
            employees=showFiltered(
                    new TransportClient()
                            .getEmployeesFrom(loc1),employees);
        } catch (IOException e) {
            //  e.printStackTrace();
        }


        try {
            employees=showFiltered(
                    new TransportClient()
                            .getEmployeesFrom(loc2),employees);
        } catch (IOException e) {
            // e.printStackTrace();
        }

        TransportListener transportListener = new TransportListener(dataServerPort,employees,true
        );
        transportListener.start();

        try {
            Thread.sleep(SECONDS.toMillis(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        transportListener.setStopped(true);

    }


    private static ArrayList<Employee> showFiltered(ArrayList<Employee> list,ArrayList<Employee> list2) {

        list2.addAll(list);

        System.out.println("[Result] -----------------------------------------\n" +
                        "Received from nodes employees: " +
                        list.stream()
                                .filter(e -> e.getSalary() > 500.0)
                                .sorted(Comparator.comparing(Employee::getLastName))
                                .collect(Collectors.groupingBy(Employee::getDepartment))
                                .toString()
        );
        return list2;
    }

}

