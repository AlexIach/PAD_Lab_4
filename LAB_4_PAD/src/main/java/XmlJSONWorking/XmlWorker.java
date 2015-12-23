package XmlJSONWorking;

import work.Employee;
import work.EmployeesContainer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;


public class XmlWorker {

    public static String EmpToXml(Employee emp){


        String finalstring = "";
        JAXBContext jaxbContext = null;
       // File file = new File("D:\\Projects\\f1.xml");

        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //jaxbMarshaller.marshal(emp, System.out);
            StringWriter outWriter = new StringWriter();
            StreamResult result = new StreamResult( outWriter );

            jaxbMarshaller.marshal(emp, result);

            StringBuffer sb = outWriter.getBuffer();
            finalstring = sb.toString();
           // System.out.println("bdusbc"+sb);
       // jaxbMarshaller.marshal(emp, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return finalstring;
    }



    public static String EmpContToXml(ArrayList<Employee> emps){


        String finalstring = "";
        JAXBContext jaxbContext = null;
        EmployeesContainer empc = new EmployeesContainer(emps);
        // File file = new File("D:\\Projects\\f1.xml");

        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.marshal(emp, System.out);
            StringWriter outWriter = new StringWriter();
            StreamResult result = new StreamResult( outWriter );

            jaxbMarshaller.marshal(emps, result);

            StringBuffer sb = outWriter.getBuffer();
            finalstring = sb.toString();
            // System.out.println("bdusbc"+sb);
            // jaxbMarshaller.marshal(emp, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return finalstring;
    }


    public static String EmpsToXml(ArrayList<Employee> emps){


        String finalstring = "";
       // JAXBContext jaxbContext = null;
        // File file = new File("D:\\Projects\\f1.xml");
        for(int i=0;i<emps.size();i++){

            finalstring=finalstring+EmpToXml(emps.get(i))+"end";

        }



        return finalstring;
    }

    public static String[] EmpsToXmlar(ArrayList<Employee> emps){


        String finalstring[] = {};
        // JAXBContext jaxbContext = null;
        // File file = new File("D:\\Projects\\f1.xml");
        for(int i=0;i<emps.size();i++){

            finalstring[i]=EmpToXml(emps.get(i));

        }



        return finalstring;
    }





    public static Employee XmlToEmp(String S){

        Employee emp = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);



            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(S.getBytes());
            emp = (Employee) jaxbUnmarshaller.unmarshal(stream);
           // System.out.println(emp);




        } catch (JAXBException e) {
            e.printStackTrace();
        }

    return emp;
    }



  //  public String JSONtoXml(){}

  //  public String XmlToJSON(){}

  /*  public static void main(String[] args){
      //  Employee n1 = new Employee("Daria", "Osadceaia", "IT", 506.0);
      //  Employee n2 = new Employee("Yellow", "Lemon", "IT", 505.0);

        //System.out.println(EmpToXml(n1));
        //System.out.println(EmpToXml(n2));
     //   String s1 = EmpToXml(n1);
     //   String s2 = EmpToXml(n2);

      //  Employee n3 = XmlToEmp(s1);
       // System.out.println(n3.toString());
        ArrayList<Employee> Employees = new ArrayList<Employee>(){{
            add(new Employee("Mark", "L", "Marketing", 500.0));
            add(new Employee("Mark", "L", "Marketing", 500.0));
            add(new Employee("Mark", "L", "Marketing", 500.0));
        }
        };

        System.out.println(EmpsToXml(Employees));


    }*/


}


