package service;

import domain.Grade;
import domain.Student;
import domain.timemanagement.YearStructure;
import domain.validator.GradeValidator;
import domain.validator.LabValidator;
import domain.validator.StudentValidator;
import exceptions.ValidationException;
import jdk.vm.ci.meta.Local;
import repository.GradeFileRepository;
import repository.LabFileRepository;
import repository.StudentFileRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class GradeService {
    private GradeFileRepository gradeFileRepository;
    private ClassBookService classBookService;

    private LabFileRepository labFileRepository;
    private LabValidator labValidator;
    private YearStructure yearStructure;
    private StudentValidator studentValidator;
    private StudentFileRepository studentFileRepository;

    public GradeService(GradeFileRepository gradeFileRepository) {
        this.gradeFileRepository = gradeFileRepository;

        studentValidator = new StudentValidator();
        studentFileRepository = new StudentFileRepository(studentValidator,
                "D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\studentsXml");
        labValidator = new LabValidator();
//        labFileRepository = new LabFileRepository(labValidator,
//                "C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\labs.txt");
        labFileRepository = new LabFileRepository(labValidator,
                "D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\labs");
        yearStructure = new YearStructure("D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\yearStructure");

        //yearStructure = new YearStructure("C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\yearStructure.txt");
//        studentFileRepository = new StudentFileRepository(studentValidator, "C:\\Users\\Andreea\\Desktop\\Facultate\\Anul II\\Subjects\\MAP\\Labs\\Project\\src\\main\\java\\data\\students.txt");
        classBookService = new ClassBookService(labFileRepository, studentFileRepository, gradeFileRepository);
    }

    public Grade search(String id) {
        return gradeFileRepository.findOne(id);
    }

    public Iterable<Grade> findAll() {
        return gradeFileRepository.findAll();
    }

    public String holiday(LocalDate date) {
        int week = yearStructure.getWeek(date);
        if (week < 1) {
            return "Vacanta";
        }
        return "";
    }

    public String maximumGrade(LocalDate date, String id) {
        int maxGrade = 10;
        List<String> cva = Arrays.asList(id.split("_"));
        Long idLab = Long.parseLong(cva.get(1));
        int deadline = labFileRepository.findOne(idLab).getDeadlineWeek();
        int intarziere = yearStructure.getWeek(date) - deadline;
        int motivations = 0;
        String msg = "";
        if (intarziere > 0 && intarziere <= 2) {
            motivations = 2 - intarziere;
            maxGrade -= intarziere;
        }

        if (motivations <= 2) {
            msg = "Maximum grade is " + maxGrade + " with " + motivations + " motivations\n";
        } else {
            msg = "Prea multe saptamani restanta\n";
        }
        return msg;
    }

    public Grade addGrade(String id, LocalDate date, String teacher, float value, boolean justification, String feedback) {
        Grade grade = new Grade(id, date, teacher, value);

        List<String> cva = Arrays.asList(id.split("_"));
        Long idLab = Long.parseLong(cva.get(1));
        Long idSt = Long.parseLong(cva.get(0));

        if (studentFileRepository.findOne(idSt) == null || labFileRepository.findOne(idLab) == null) {
            return grade;
        }

        //diminuarea notei cu cate un punct
        int gradeDate = yearStructure.getWeek(date);
        int deadline = labFileRepository.findOne(idLab).getDeadlineWeek();
        int intarziere = gradeDate - deadline;
        float copieValue = value;
        if (intarziere <= 2 && intarziere > 0) {
            value -= intarziere;
        }
        if (justification == true && maximumGrade(date, String.valueOf(idSt) + "_" + String.valueOf(idLab)).startsWith("Maximum")) {
            value = copieValue;
        } else {
            return grade;
        }
        //calcularea automata a notei maxime posibile


        //daca a intarziat proful sau nu
        if (yearStructure.getWeek(date) - yearStructure.getWeek(LocalDate.now()) > 1) {
            throw new ValidationException("Note adaugate tarziu de profesor \n");
        }

        Grade g = gradeFileRepository.save(grade);
        if (g == null) {
            classBookService.add(idSt, idLab, value, date, deadline, feedback);
            return null;
        }
        return grade;
    }


}
