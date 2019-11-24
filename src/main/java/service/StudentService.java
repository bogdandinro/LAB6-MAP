package service;

import domain.Student;
import repository.StudentFileRepository;

public class StudentService {
    StudentFileRepository studentFileRepository;

    public StudentService(StudentFileRepository studentFileRepository) {
        this.studentFileRepository = studentFileRepository;
    }

    public Student search(Long id){
        return studentFileRepository.findOne(id);
    }

    public Iterable<Student> findAll(){
        return studentFileRepository.findAll();
    }

    public Student add(Long id, String firstName, String secondName, int group, String email, String teacherLab){
        Student student = new Student(id, firstName, secondName, group, email, teacherLab);
        return studentFileRepository.save(student);
    }

    public Student delete(Long id){
        return studentFileRepository.delete(id);
    }

    public Student update(Long id, String firstName, String secondName, int group, String email, String teacherLab){
        Student student = new Student(id, firstName, secondName, group, email, teacherLab);
        return studentFileRepository.update(student);
    }
}
