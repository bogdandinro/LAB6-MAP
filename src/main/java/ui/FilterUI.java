package ui;

import exceptions.ValidationException;
import service.FilterService;

import java.util.Scanner;

public class FilterUI {
    private FilterService filterService;

    public FilterUI(FilterService filterService) {
        this.filterService = filterService;
    }

    public void runFilter(){
        boolean okk = true;
        while (okk) {
            try {
                System.out.println("1. Toti studentii de la un anumit grup \n" +
                        "2. Toti studentii cu un anumit laborator\n" +
                        "3. Toti studentii care au laboratorul cu un anumit prof\n" +
                        "4. Toate notele dintro saptamana \n" +
                        "5. Exit\n");
                Scanner in = new Scanner(System.in);
                int cmd = Integer.parseInt(in.nextLine());
                switch (cmd) {
                    case 1:
                        System.out.println("Dati grupa: ");
                        System.out.println(filterService.studentsByGroup(in.nextInt()).toString());
                        break;
                    case 2:
                        System.out.println("Id-ul laboratorului: ");
                        System.out.println(filterService.studentsByLab(in.nextLong()).toString());
                        break;
                    case 3:
                        System.out.println("Id-ul laboratorului + Numele profesorului : ");
                        System.out.println(filterService.studentsByLabAndTeacher(in.nextLong(),in.next()).toString());
                        break;
                    case 4:
                        System.out.println("Id-ul laboratorului si saptamana : ");
                        System.out.println(filterService.gradesByLabAndWeek(in.nextLong(),in.nextInt()).toString());
                        break;
                    case 5:
                        System.out.println("Inapoi ");
                        okk = false;
                        break;
                    default:
                        System.out.println("Ceva");
                }
            }
            catch (IllegalArgumentException | ValidationException e) {
                System.out.println(e);
            }
        }
    }
}
