package work;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;


@XmlRootElement
public class EmployeesContainer implements Serializable{

    @XmlElement
    public ArrayList<Employee> EmployeesCont;

    public EmployeesContainer() {
        EmployeesCont = new ArrayList<Employee>();
    }

    public EmployeesContainer(ArrayList<Employee> v) {
        EmployeesCont = v;
    }
}
