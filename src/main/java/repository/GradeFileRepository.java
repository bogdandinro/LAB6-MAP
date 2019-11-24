package repository;

import domain.Grade;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class GradeFileRepository extends AbstractFileRepository<String, Grade> {
    public GradeFileRepository(Validator<Grade> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Grade delete(String s) {
        return super.delete(s);
    }

    protected void deserializeFromXML(Document document) {
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("grade");
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Grade grade = new Grade(
                        element.getElementsByTagName("id").item(0).getTextContent(),
                        LocalDate.parse(element.getElementsByTagName("date").item(0).getTextContent()),
                        element.getElementsByTagName("teacher").item(0).getTextContent(),
                        Float.parseFloat(element.getElementsByTagName("value").item(0).getTextContent()));
                save(grade);
            }
        }
    }

    @Override
    protected void serializeToXML(Grade grade, Element root, Document document) {
            Element gr = document.createElement("grade");
            root.appendChild(gr);

            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(grade.getId()));
            gr.appendChild(id);

            Element date = document.createElement("date");
            date.appendChild(document.createTextNode(String.valueOf(grade.getDate())));
            gr.appendChild(date);

            Element teacher = document.createElement("teacher");
            teacher.appendChild(document.createTextNode(grade.getTeacher()));
            gr.appendChild(teacher);

            Element value = document.createElement("value");
            value.appendChild(document.createTextNode(String.valueOf(grade.getValue())));
            gr.appendChild(value);
    }


}
