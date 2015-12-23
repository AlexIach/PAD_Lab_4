package XmlJSONWorking;

import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;


public class XmlValidator {


 /*   public static void validation(String xml){

        // 1. ����� � �������� ���������� ������� ��� ����� XML Schema
        SchemaFactory factory =
                SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        // 2. ���������� �����
        // ����� ����������� � ������ ���� java.io.File, �� �� ����� ������ ������������
        // ������ java.net.URL � javax.xml.transform.Source
        File schemaLocation = new File("D:\\Projects\\schema1.xsd");
        Schema schema = null;
        try {
            schema = factory.newSchema(schemaLocation);

        // 3. �������� ���������� ��� �����
        Validator validator = schema.newValidator();

        // 4. ������ ������������ ���������
        Source source = new StreamSource(xml);

        // 5. ��������� ���������

            validator.validate(source);
            System.out.println(xml + " is valid.");
        }


        catch (SAXException ex) {
            System.out.println(xml + " is not valid because ");
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/







    public static void validation(String xml1) {


        StringReader reader = new StringReader(xml1);
       // URL xsdResource = ValidatorXML.class.getClassLoader().getResource("D:\\Projects\\schema1.xsd");
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        try {
        Schema myschema=factory.newSchema(new File("D:\\Projects\\schema1.xsd"));
        Validator val = myschema.newValidator();

            val.validate(new StreamSource(reader));
            System.out.println(xml1 + " is valid.");



        } catch (SAXException e) {

            System.out.println(xml1 + " is not valid because ");

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


  //  public static void main(String[] args){




  //  }



    }



