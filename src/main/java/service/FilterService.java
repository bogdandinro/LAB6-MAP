package service;

import domain.Grade;
import domain.Student;
import domain.timemanagement.YearStructure;
import domain.validator.StudentValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterService {
    private StudentService studentService;
    private GradeService gradeService;
    private YearStructure yearStructure;

    public FilterService(StudentService studentService, GradeService gradeService, YearStructure yearStructure) {
        this.studentService = studentService;
        this.gradeService = gradeService;
        this.yearStructure = yearStructure;
    }

    public List<Student> studentsByGroup(int group) {
        Iterable<Student> list = studentService.findAll();
        List<Student> students = new  ArrayList<>();
        for(Student st:list){
            students.add(st);
        }

        return students.stream()
                .filter(student -> student.getGroup() == group)
                .collect(Collectors.toList());
    }

    public List<Student> studentsByLab(Long id) {
        Iterable<Grade> list = gradeService.findAll();
        List<Grade> grades = new  ArrayList<>();
        for(Grade gr:list){
            grades.add(gr);
        }

        return grades.stream()
                .filter(grade -> grade.getIdLab() == id)
                .map(grade -> studentService.search(grade.getIdStudent()))
                .collect(Collectors.toList());
    }

    public List<Student> studentsByLabAndTeacher(Long id, String teacher) {
        Iterable<Grade> list = gradeService.findAll();
        List<Grade> grades = new  ArrayList<>();
        for(Grade gr:list){
            grades.add(gr);
        }

        return grades.stream()
                .filter(grade -> grade.getIdLab() == id && grade.getTeacher().equals(teacher))
                .map(grade -> studentService.search(grade.getIdStudent()))
                .collect(Collectors.toList());
    }

    public List<Grade> gradesByLabAndWeek(Long id, int week) {
        Iterable<Grade> list = gradeService.findAll();
        List<Grade> grades = new  ArrayList<>();
        for(Grade gr:list){
            grades.add(gr);
        }

        return grades.stream()
                .filter(grade -> grade.getIdLab() == id && yearStructure.getWeek(grade.getDate()) == week)
                .collect(Collectors.toList());
    }


}
