package repository;

import domain.Student;
import domain.validator.Validator;
import org.w3c.dom.*;


public class StudentFileRepository extends AbstractFileRepository<Long, Student> {
    public StudentFileRepository(Validator<Student> validator, String path) {
        super(validator, path);
    }

    protected void deserializeFromXML(Document document) {
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("student");
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Student student = new Student(
                        Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()),
                        element.getElementsByTagName("firstname").item(0).getTextContent(),
                        element.getElementsByTagName("secondname").item(0).getTextContent(),
                        Integer.parseInt(element.getElementsByTagName("group").item(0).getTextContent()),
                        element.getElementsByTagName("email").item(0).getTextContent(),
                        element.getElementsByTagName("teacher").item(0).getTextContent());
                save(student);
            }
        }
    }

    protected void serializeToXML(Student s, Element root, Document document) {
        Element student = document.createElement("student");
        root.appendChild(student);

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(String.valueOf(s.getId())));
        student.appendChild(id);

        Element firstName = document.createElement("firstname");
        firstName.appendChild(document.createTextNode(s.getFirstName()));
        student.appendChild(firstName);

        Element secondName = document.createElement("secondname");
        secondName.appendChild(document.createTextNode(s.getSecondName()));
        student.appendChild(secondName);

        Element group = document.createElement("group");
        group.appendChild(document.createTextNode(String.valueOf(s.getGroup())));
        student.appendChild(group);

        Element email = document.createElement("email");
        email.appendChild(document.createTextNode(s.getEmail()));
        student.appendChild(email);

        Element teacher = document.createElement("teacher");
        teacher.appendChild(document.createTextNode(s.getTeacherLab()));
        student.appendChild(teacher);
    }


}
