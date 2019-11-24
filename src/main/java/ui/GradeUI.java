package ui;

import domain.Grade;
import domain.validator.GradeValidator;
import exceptions.ValidationException;
import org.graalvm.compiler.hotspot.BootstrapWatchDog_OptionDescriptors;
import repository.GradeFileRepository;
import repository.LabFileRepository;
import service.GradeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GradeUI {
    private GradeService gradeService;

    public GradeUI(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    private List<String> read(int sizeList) {
        Scanner in = new Scanner(System.in);
        String params = in.nextLine();
        List<String> list = Arrays.asList(params.split(";"));
        if (list.size() != sizeList) {
            System.out.println("Numarul de parametrii incorect \n");
        }
        return list;
    }

    private void addGrade() {
        System.out.println("Introduceti toate informatiiile pentru o nota (id, data, teacher, value, justification, feedback\n" +
                "Use ';' to separate the data and true or false for justification\n");
        List<String> list = read(6);
        String id = list.get(0);
        LocalDate date = LocalDate.parse(list.get(1));
        String msg1 = gradeService.holiday(date);
        if(!msg1.isEmpty()){
            System.out.println(msg1);
            return;
        }
        String teacher = list.get(2);
        int value = Integer.parseInt(list.get(3));
        boolean justification = Boolean.parseBoolean(list.get(4));
        String feedback = list.get(5);
        String msg = gradeService.maximumGrade(date, id);
        System.out.println(msg);

        Grade g = gradeService.addGrade(id, date, teacher, value, justification, feedback);
        if (g == null) {
            System.out.println(("Grade saved\n"));
        } else {
            System.out.println("Grade can't be saved\n");
        }
    }

    private void findGrade() {
        System.out.println("Enter the ID of the grade you want to find from file\n");
        List<String> list = read(1);
        String id = list.get(0);

        Grade g = gradeService.search(id);
        if (g == null) {
            System.out.println("This grade doesn't exist.\n");
        } else {
            System.out.println("The grade you're searching:\t" + g.toString() + '\n');
        }
    }

    private void printAll(){
        gradeService.findAll().forEach(x -> System.out.println(x.toString()));
    }

    public void runGrade() {
        boolean ok = true;
        while (ok) {
            try {
                System.out.println("1. Add\n2. Delete\n3. Find\n4. Update\n5. Print all\n6. Exit");
                Scanner in1 = new Scanner(System.in);
                int cmd1 = Integer.parseInt(in1.nextLine());
                switch (cmd1) {
                    case 1:
                        addGrade();
                        break;
                    case 2:
                        //deleteStudent();
                        break;
                    case 3:
                        findGrade();
                        break;
                    case 4:
                        //updateStudent();
                        break;
                    case 5:
                        printAll();
                        break;
                    case 6:
                        ok = false;
                        break;
                    default:
                        System.out.println("Enter a number between 1 and 6. Check the menu again! :)\n");
                }
            } catch (IllegalArgumentException | ValidationException e) {
                System.out.println(e);
            }
        }
    }

}
