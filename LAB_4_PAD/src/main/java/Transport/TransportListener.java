package Transport;


import work.Employee;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import static XmlJSONWorking.JSONWorker.EmpToJSON;
import static XmlJSONWorking.XmlWorker.EmpsToXml;
import static work.SerializationUtils.serialize;



//import static com.utm.pad.d2c.transport.SerializationUtils.serialize;
//import static org.apache.commons.lang3.SerializationUtils.serialize;

public class TransportListener extends Thread {
    private int serverPort;
    private boolean isStopped;
    private boolean isAccepted;
    ServerSocket serverSocket;
    private boolean isMaven = false;



    public ArrayList<Employee> Employees = new ArrayList<Employee>() {{
        add(new Employee("", "", "", 0.0));
    }};

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean isStopped) {
        this.isStopped = isStopped;
        if (!isAccepted) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public TransportListener(int serverPort, ArrayList<Employee> E) {
        Employees = E;
        this.serverPort = serverPort;
        isStopped = false;
    }


    public TransportListener(int serverPort, ArrayList<Employee> E,boolean isMav) {
        Employees = E;
        this.serverPort = serverPort;
        isStopped = false;
        isMaven = isMav;
    }

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(serverPort);
            while (!isStopped) {
                Socket socket = serverSocket.accept();  // Blocking call!
                // You can use non-blocking approach
                isAccepted = true;
                //Employee[] s = new Employee[getEmployees().size()];
                String s1;
                //s1 = EmpToJSON(Employees);
                if(isMaven) {
                    s1 = EmpsToXml(Employees);
                }
                else {


                    s1 = EmpToJSON(Employees);
                }
                serialize(s1, socket.getOutputStream());
                socket.close();
                isAccepted = false;
            }
        } catch (SocketException e) {
            System.out.println("[WARNING] ----------------------------------------- \n" +
                    "[WARNING] Waiting time expired... Socket is closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployees() {
        return Employees;
    }
}
