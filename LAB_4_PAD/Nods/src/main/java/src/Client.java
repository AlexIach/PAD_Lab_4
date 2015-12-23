package src;

import Discovery.DiscoveryClient;
import Transport.TransportClient;
import work.Employee;
import work.Location;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static XmlJSONWorking.XmlValidator.validation;

public class Client {
    public static void main(String[] args) {
        String xml;

        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Client is running...");

        try {

            Location location = new DiscoveryClient(new Location(
                    new InetSocketAddress("127.0.0.1", 33333))).retrieveLocation();
            System.out.println("[INFO] -----------------------------------------\n" +
                    "[INFO] Discovered nod(Maven): " + location);

            if (location != null) {

                TransportClient c = new TransportClient();
               xml =  c.getXmlFrom(location);
                System.out.println("[Received] -----------------------------------------\n"
                );

               // String[] parts = xml.split("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                String[] parts = xml.split("end");
                //System.out.println(parts.length+"-----------------------------------------"+    parts[0]+"\n");


                for(int i=0;i<parts.length;i++){
                    validation(parts[i]);
                    System.out.println("[Validation] -----------------------------------------\n");
                }

            }
            else
                System.out.println("location is null");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }







//----------------------------------------------------------------------------------------------------------------------------------
    private static void showFiltered(ArrayList<Employee> list) {
        System.out.println("[Result] -----------------------------------------\n" +
                        "Discovered employees: " +
                        list.stream()
                                .filter(e -> e.getSalary() > 500.0)
                                .sorted(Comparator.comparing(Employee::getLastName))
                                .collect(Collectors.groupingBy(Employee::getDepartment))
                                .toString()
        );
    }
}
