package ui;

import domain.Student;
import domain.validator.StudentValidator;
import repository.StudentFileRepository;
import service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentUI {
    private StudentService studentService;

    public StudentUI(StudentService studentService) {
        this.studentService = studentService;
    }

    private List<String> read(int sizeList) {
        Scanner in = new Scanner(System.in);
        String params = in.nextLine();
        List<String> list = Arrays.asList(params.split(";"));
        if (list.size() != sizeList) {
            throw new IllegalArgumentException("Number of parameters is incorrect. Try again!\n");
        }
        return list;
    }

    private void addStudent() {
        System.out.println("Enter all details about the student (id, firstName, secondName, group, email, teacherLab\n" +
                "Use ';' to separate the data\n");
        List<String> list = read(6);
        Long id = Long.parseLong(list.get(0));
        String firstName = list.get(1);
        String secondName = list.get(2);
        int group = Integer.parseInt(list.get(3));
        String email = list.get(4);
        String teacherLab = list.get(5);

        Student s = studentService.add(id, firstName, secondName, group, email, teacherLab);
        if (s == null) {
            System.out.println(("Student saved\n"));
        } else {
            System.out.println("Student can't be saved\n");
        }
    }

    private void deleteStudent() {
        System.out.println("Enter the ID of the student you want to remove from the file\n");
        List<String> list = read(1);
        Long id = Long.parseLong(list.get(0));

        Student s = studentService.delete(id);
        if (s == null) {
            System.out.println(("Student deleted!\n"));
        } else {
            System.out.println("Student doesn't exist.\n");
        }
    }

    private void findStudent() {
        System.out.println("Enter the ID of the student you want to find from file\n");
        List<String> list = read(1);
        Long id = Long.parseLong(list.get(0));

        Student s = studentService.search(id);
        if(s == null){
            System.out.println("This student doesn't exist.\n");
        }
        else{
            System.out.println("The student you're searching:\t" + s.toString() + '\n');
        }
    }

    public void updateStudent() {
        System.out.println("Enter the id of the student you want to update and the new details: ");
        List<String> list = read(6);
        Long id = Long.parseLong(list.get(0));
        String firstName = list.get(1);
        String secondName = list.get(2);
        int group = Integer.parseInt(list.get(3));
        String email = list.get(4);
        String teacherLab = list.get(5);

        Student s = studentService.update(id, firstName, secondName, group, email, teacherLab);
        if (s == null) {
            System.out.println(("Student updated"));
        } else {
            System.out.println("Student doesn't exist.\n");
        }
    }

    private void printAll(){
        System.out.println("Students:");
        Iterable<Student> list = studentService.findAll();
        list.forEach(x -> System.out.println(x.toString()));
    }

    public void runStudent(){
        boolean ok = true;
        while(ok) {
            System.out.println("1. Add\n2. Delete\n3. Find\n4. Update\n5. Print all\n6. Exit");
            Scanner in1 = new Scanner(System.in);
            int cmd1 = Integer.parseInt(in1.nextLine());
            switch (cmd1) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    findStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    printAll();
                    break;
                case 6:
                    ok = false;
                    break;
                default:
                    System.out.println("Enter a number between 1 and 5. Check the menu again! :)\n");
            }
        }
    }
}
