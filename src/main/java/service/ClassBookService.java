package service;

import domain.Grade;
import domain.Student;
import domain.validator.LabValidator;
import domain.validator.StudentValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import repository.GradeFileRepository;
import repository.LabFileRepository;
import repository.StudentFileRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ClassBookService {
    private StudentFileRepository studentFileRepository;
    private LabFileRepository labFileRepository;
    private GradeFileRepository gradeFileRepository;

    public ClassBookService(LabFileRepository labFileRepository, StudentFileRepository studentFileRepository, GradeFileRepository gradeFileRepository) {
        this.labFileRepository = labFileRepository;
        this.studentFileRepository = studentFileRepository;
        this.gradeFileRepository = gradeFileRepository;


    }

    public void add(Long idSt, Long idLab, float value, LocalDate date, int deadline, String feedback) {
        Student s = studentFileRepository.findOne(idSt);
        Grade g = gradeFileRepository.findOne(String.valueOf(idSt) + String.valueOf(idLab));
        String path = "D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\datastudentsXML\\" + s.getFirstName() + s.getSecondName() + ".xml";
        if (g == null) {
            File file = new File(path);
        }
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //root
            Element root = document.createElement("entity");
            document.appendChild(root);

            Element grade = document.createElement("grade");
            root.appendChild(grade);

            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(String.valueOf(idLab)));
            grade.appendChild(id);

            Element value1 = document.createElement("value");
            value1.appendChild(document.createTextNode(String.valueOf(value)));
            grade.appendChild(value1);

            Element date1 = document.createElement("date");
            date1.appendChild(document.createTextNode(String.valueOf(date)));
            grade.appendChild(date1);

            Element deadlineWeek = document.createElement("deadline");
            deadlineWeek.appendChild(document.createTextNode(String.valueOf(deadline)));
            grade.appendChild(deadlineWeek);

            Element feedback1 = document.createElement("feedback");
            feedback1.appendChild(document.createTextNode(String.valueOf(feedback)));
            grade.appendChild(feedback1);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(path);

            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}