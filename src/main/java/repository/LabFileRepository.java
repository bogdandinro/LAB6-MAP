package repository;

import domain.Lab;
import domain.Student;
import domain.validator.Validator;
import org.w3c.dom.*;

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
import java.util.Arrays;
import java.util.List;

public class LabFileRepository extends AbstractFileRepository<Long, Lab> {
    public LabFileRepository(Validator<Lab> validator, String path) {
        super(validator, path);
    }

    protected void deserializeFromXML(Document document) {
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("lab");
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Lab lab = new Lab(
                        Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()),
                        element.getElementsByTagName("description").item(0).getTextContent(),
                        Integer.parseInt(element.getElementsByTagName("startweek").item(0).getTextContent()),
                        Integer.parseInt(element.getElementsByTagName("deadlineweek").item(0).getTextContent()));
                save(lab);
            }
        }
    }

    @Override
    protected void serializeToXML(Lab lab, Element root, Document document) {
        Element laborator = document.createElement("lab");
        root.appendChild(laborator);

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(String.valueOf(lab.getId())));
        laborator.appendChild(id);

        Element description = document.createElement("description");
        description.appendChild(document.createTextNode(lab.getDescription()));
        laborator.appendChild(description);

        Element startWeek = document.createElement("startweek");
        startWeek.appendChild(document.createTextNode(String.valueOf(lab.getStartWeek())));
        laborator.appendChild(startWeek);

        Element deadlineWeek = document.createElement("deadlineweek");
        deadlineWeek.appendChild(document.createTextNode(String.valueOf(lab.getDeadlineWeek())));
        laborator.appendChild(deadlineWeek);
    }


}
