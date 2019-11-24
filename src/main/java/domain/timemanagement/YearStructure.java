package domain.timemanagement;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class YearStructure {
    private SemesterStructure semester1;
    private SemesterStructure semester2;
    private String fileName;

    public YearStructure(String fileName) {
        this.fileName = fileName;
        loadSemester();
    }

    private void loadSemester() {
        try {
            File file = new File(fileName);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("semesterstructure");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (semester1 == null) {
                        semester1 = new SemesterStructure(LocalDate.parse(element.getElementsByTagName("startsemester").item(0).getTextContent()),
                                LocalDate.parse(element.getElementsByTagName("startholiday").item(0).getTextContent()),
                                LocalDate.parse(element.getElementsByTagName("endholiday").item(0).getTextContent()),
                                LocalDate.parse(element.getElementsByTagName("endsemester").item(0).getTextContent()));
                    }
                    else{
                        semester2 = new SemesterStructure(LocalDate.parse(element.getElementsByTagName("startsemester").item(0).getTextContent()),
                                LocalDate.parse(element.getElementsByTagName("startholiday").item(0).getTextContent()),
                                LocalDate.parse(element.getElementsByTagName("endholiday").item(0).getTextContent()),
                                LocalDate.parse(element.getElementsByTagName("endsemester").item(0).getTextContent()));
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getWeek(LocalDate date) {
        if (date.isAfter(semester1.getStartSemester()) && date.isBefore(semester1.getEndSemester())) {
            return semester1.getCurrentWeek(date);
        } else {
            return semester2.getCurrentWeek(date);
        }
    }


}
