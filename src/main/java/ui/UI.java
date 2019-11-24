package ui;

import domain.ClassBook;
import domain.timemanagement.YearStructure;
import domain.validator.GradeValidator;
import domain.validator.LabValidator;
import domain.validator.StudentValidator;
import exceptions.ValidationException;
import repository.GradeFileRepository;
import repository.LabFileRepository;
import repository.StudentFileRepository;
import service.*;

import java.io.File;
import java.util.Scanner;

public class UI {
    private StudentService studentService;
    private StudentFileRepository studentFileRepository;
    private StudentValidator studentValidator;

    private LabValidator labValidator;
    private LabFileRepository labFileRepository;
    private LabService labService;
    private YearStructure yearStructure;

    private GradeFileRepository gradeFileRepository;
    private GradeValidator gradeValidator;
    private GradeService gradeService;

    private ClassBookService classBookService;

    private StudentUI sui;
    private LabUI lui;
    private GradeUI grui;
    private FilterUI fui;

    public UI() {
        studentValidator = new StudentValidator();
        studentFileRepository = new StudentFileRepository(studentValidator,
                "D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\studentsXml");
        //studentFileRepository = new StudentFileRepository(studentValidator, "C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\students.txt");
        studentService = new StudentService(studentFileRepository);

        labValidator = new LabValidator();
        labFileRepository = new LabFileRepository(labValidator,
                "D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\labs");
        //labFileRepository = new LabFileRepository(labValidator, "C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\labs.txt");
        yearStructure = new YearStructure("D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\yearStructure");
        //yearStructure = new YearStructure("C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\yearStructure.txt");
        labService = new LabService(labFileRepository, yearStructure);

        gradeValidator = new GradeValidator();
        gradeFileRepository = new GradeFileRepository(gradeValidator,
                "D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\grades");
        //gradeFileRepository = new GradeFileRepository(gradeValidator, "C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\grades.txt");
        gradeService = new GradeService(gradeFileRepository);

        classBookService = new ClassBookService(labFileRepository, studentFileRepository, gradeFileRepository);

        sui = new StudentUI(studentService);
        lui = new LabUI(labService, yearStructure);
        grui = new GradeUI(gradeService);

        FilterService filterService = new FilterService(studentService, gradeService, yearStructure);
        fui = new FilterUI(filterService);
    }


    public void run() {
            boolean okk = true;
            while (okk) {
                try {
                    System.out.println("1. Student\n2. Lab\n3. Grade\n4. Filter\n5. Exit\n");
                    Scanner in = new Scanner(System.in);
                    int cmd = Integer.parseInt(in.nextLine());
                    switch (cmd) {
                        case 1:
                            sui.runStudent();
                            break;
                        case 2:
                            lui.runLab();
                            break;
                        case 3:
                            grui.runGrade();
                            break;
                        case 4:
                            fui.runFilter();
                            break;
                        case 5:
                            System.out.println("Gata");
                            okk = false;
                            break;
                        default:
                            System.out.println("Input standard ");
                    }
                }
                catch (IllegalArgumentException | ValidationException e) {
                    System.out.println(e);
                }
            }
    }

}
