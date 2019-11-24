import domain.Lab;
import domain.Student;
import domain.timemanagement.YearStructure;
import domain.validator.LabValidator;
import domain.validator.StudentValidator;
import exceptions.ValidationException;
import repository.LabFileRepository;
import repository.StudentFileRepository;
import service.LabService;
import ui.UI;

import java.time.LocalDate;
import java.time.Year;

public class Main {
    public static void main(String[] args){
        /*Student s1 = new Student(1l,"Stefan", "Buciu", 221, "bsir2222@scs.ubbcluj.ro","Andreea");
        StudentValidator validator = new StudentValidator();
        StudentFileRepository repository = new StudentFileRepository(validator,"C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\students.txt");
        repository.save(s1);

        YearStructure yearStructure = new YearStructure("C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\yearStructure.txt");
        Lab lab = new Lab(2l,"Wonderful", yearStructure.getWeek(LocalDate.now()), 9);
        LabValidator val = new LabValidator();
        LabFileRepository repoLab = new LabFileRepository(val,"C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\labs.txt");
        repoLab.save(lab);

        LabService service = new LabService(repoLab, yearStructure);
        Lab lab2 = new Lab(2l,"ioooooi",10,12);
        try{
            service.update(lab2);
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        }*/
        UI ui = new UI();
        ui.run();
    }
}
