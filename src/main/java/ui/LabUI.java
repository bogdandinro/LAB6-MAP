package ui;

import domain.Lab;
import domain.Student;
import domain.timemanagement.YearStructure;
import domain.validator.LabValidator;
import repository.LabFileRepository;
import service.LabService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LabUI {
    private LabService labService;
    private YearStructure yearStructure;

    public LabUI(LabService labService, YearStructure yearStructure) {
        this.labService = labService;
        this.yearStructure = yearStructure;
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

    private void addLab() {
        System.out.println("Enter all details about the lab (id, description and deadlineWeek\n" +
                "Use ';' to separate the data\n");
        List<String> list = read(3);
        Long id = Long.parseLong(list.get(0));
        String description = list.get(1);
        int deadlineWeek = Integer.parseInt(list.get(2));
        int startWeek = yearStructure.getWeek(LocalDate.now());

        Lab l = labService.add(id, description, startWeek, deadlineWeek);
        if (l == null) {
            System.out.println(("Lab saved\n"));
        } else {
            System.out.println("Lab can't be saved\n");
        }
    }

    private void deleteLab() {
        System.out.println("Enter the ID of the lab you want to remove from the file\n");
        List<String> list = read(1);
        Long id = Long.parseLong(list.get(0));

        Lab l = labService.delete(id);
        if (l == null) {
            System.out.println(("Lab deleted!\n"));
        } else {
            System.out.println("Lab doesn't exist.\n");
        }
    }

    private void findLab() {
        System.out.println("Enter the ID of the lab you want to find from file\n");
        List<String> list = read(1);
        Long id = Long.parseLong(list.get(0));

        Lab l = labService.search(id);
        if (l == null) {
            System.out.println("This lab doesn't exist.\n");
        } else {
            System.out.println("The lab you're searching:\t" + l.toString() + '\n');
        }
    }

    public void updateLab() {
        System.out.println("Enter the id of the student you want to update and the new details: ");
        List<String> list = read(6);
        Long id = Long.parseLong(list.get(0));
        String description = list.get(1);
        int deadlineWeek = Integer.parseInt(list.get(2));
        int startWeek = yearStructure.getWeek(LocalDate.now());

        Lab l = labService.add(id, description, startWeek, deadlineWeek);
        if (l == null) {
            System.out.println(("Lab updated"));
        } else {
            System.out.println("Lab doesn't exist.\n");
        }
    }

    private void printAll() {
        System.out.println("Labs:");
        Iterable<Lab> list = labService.findAll();
        list.forEach(x -> System.out.println(x.toString()));
    }

    public void runLab() {
        boolean ok = true;
        while (ok) {
            System.out.println("1. Add\n2. Delete\n3. Find\n4. Update\n5. Print all\n6. Exit");
            Scanner in1 = new Scanner(System.in);
            int cmd1 = Integer.parseInt(in1.nextLine());
            switch (cmd1) {
                case 1:
                    addLab();
                    break;
                case 2:
                    deleteLab();
                    break;
                case 3:
                    findLab();
                    break;
                case 4:
                    updateLab();
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
