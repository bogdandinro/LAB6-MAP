package repository;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import service.StudentService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateXMLFile {
    public static final String path = "D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\studentsXml";
    public StudentService studentService;

    public CreateXMLFile(StudentService studentService) {
        this.studentService = studentService;
    }

    public static void main(String[] args) {

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //root
            Element root = document.createElement("students");
            document.appendChild(root);

            Element student = document.createElement("student");
            root.appendChild(student);

            Attr attribute = document.createAttribute("id");
            attribute.setValue("1");
            student.setAttributeNode(attribute);

            Element firstName = document.createElement("firstname");
            firstName.appendChild(document.createTextNode("Bogdan"));
            student.appendChild(firstName);

            Element secondName = document.createElement("secondname");
            secondName.appendChild(document.createTextNode("Ognean"));
            student.appendChild(secondName);

            Element group = document.createElement("group");
            group.appendChild(document.createTextNode("225"));
            student.appendChild(group);

            Element email = document.createElement("email");
            email.appendChild(document.createTextNode("obir@scs.ubbcluj.ro"));
            student.appendChild(email);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(path));

            transformer.transform(domSource, streamResult);
            System.out.println("Salvat");
        }
        catch (ParserConfigurationException pce){
            pce.printStackTrace();
        }catch (TransformerException tfe){
            tfe.printStackTrace();
        }
    }
}
