package Transport;


import work.Employee;
import work.Location;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static XmlJSONWorking.JSONWorker.JSONtoEmpp;
import static work.SerializationUtils.deserialize;

public class TransportClient {
    

    public ArrayList<Employee> getEmployeesFrom(Location location) throws IOException {
        Socket socket = new Socket();
        socket.connect(location.getLocation());
        String s1 = (String)deserialize(socket.getInputStream());
        System.out.print(s1);
        ArrayList<Employee>  employees = JSONtoEmpp(s1);
       // Employee[] employees = (Employee[]) deserialize(socket.getInputStream());
        socket.close();
       // return new ArrayList<Employee>(Arrays.asList(employees));
        return employees;
    }


    public String getXmlFrom(Location location) throws IOException {
        Socket socket = new Socket();
        ArrayList<Employee> employees=null;
        socket.connect(location.getLocation());
        String s1 = (String) deserialize(socket.getInputStream());

        // Employee[] employees = (Employee[]) deserialize(socket.getInputStream());
        socket.close();
        // return new ArrayList<Employee>(Arrays.asList(employees));
        return s1;
    }

}
