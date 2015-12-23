package XmlJSONWorking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import work.Employee;
import work.EmployeesContainer;

import java.io.*;
import java.util.ArrayList;


public class JSONWorker {


    public static Employee JSONtoEmp(String js) {


        Employee emp = new Gson().fromJson(js, Employee.class);
        return emp;
    }

    public static ArrayList<Employee> JSONtoEmpp(String js) {


        EmployeesContainer emp = new Gson().fromJson(js, EmployeesContainer.class);
        ArrayList<Employee> a = (ArrayList<Employee>)emp.EmployeesCont;
        return a;
    }

    public static String EmpToJSON(Employee emp) {

        Gson gson = new GsonBuilder()
                .create();
        String json = gson.toJson(emp);
        return json;
    }

    public static String EmpToJSON(Employee[] emp) {

        Gson gson = new GsonBuilder()
                .create();
        EmployeesContainer c = new EmployeesContainer();

        //new Employee[getEmployees().size()];
        String json = gson.toJson(c);
        return json;
    }

    public static String EmpToJSON(ArrayList<Employee> emp) {

        Gson gson = new GsonBuilder()
                .create();
        EmployeesContainer c = new EmployeesContainer();
        c.EmployeesCont = emp;
        //new Employee[getEmployees().size()];
        String json = gson.toJson(c);
        return json;
    }


    public static String ReadJSON(String patch) {

        File file = new File(patch);
        String S = null;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(patch));
        String line;
        while ((line = reader.readLine()) != null) {
            S+=line;
        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return S;
    }


 /*   public static void main(String[] args){
        Employee n1 = new Employee("Daria", "Osadceaia", "IT", 506.0);
        Employee n2 = new Employee("Yellow", "Lemon", "IT", 505.0);

        System.out.println(EmpToJSON(n1));
        System.out.println(EmpToJSON(n2));

        ArrayList<Employee> Employees = new ArrayList<Employee>();
        Employees.add(JSONtoEmp(ReadJSON("D:\\Projects")));

        System.out.println("[Result] -----------------------------------------\n" +
                        "Discovered employees: " +
                        Employees.stream()
                                .filter(e -> e.getSalary() > 500.0)
                                .sorted(Comparator.comparing(Employee::getLastName))
                                .collect(Collectors.groupingBy(Employee::getDepartment))
                                .toString()
        );

        
    }*/
}