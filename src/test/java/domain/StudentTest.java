package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class StudentTest {
    Student student;

    @Before
    public void setUp() throws Exception {
        student = new Student(1l, "Cristina","Male", 226, "mcir@scs.ubbcluj.ro", "Lascu");
        student.setId(1l);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getFirstName() {
        assertEquals(student.getFirstName(), "Cristina");
    }

    @Test
    public void setFirstName() {
        student.setFirstName("Cristiana");
        assertEquals(student.getFirstName(), "Cristiana");
    }

    @Test
    public void getSecondName() {
        assertEquals(student.getSecondName(), "Male");
    }

    @Test
    public void setSecondName() {
        student.setSecondName("Malo");
        assertEquals(student.getSecondName(), "Malo");
    }

    @Test
    public void getGroup() {
        assertEquals(student.getGroup(), 226);
    }

    @Test
    public void setGroup() {
        student.setGroup(225);
        assertEquals(student.getGroup(), 225);
    }

    @Test
    public void getEmail() {
        assertEquals(student.getEmail(),"mcir@scs.ubbcluj.ro");
    }

    @Test
    public void setEmail() {
        student.setEmail("mcie@scs.ubbcluj.ro");
        assertEquals(student.getEmail(), "mcie@scs.ubbcluj.ro");
    }

    @Test
    public void getTeacherLab() {
        assertEquals(student.getTeacherLab(), "Lascu");
    }

    @Test
    public void setTeacherLab() {
        student.setTeacherLab("Pop");
        assertEquals(student.getTeacherLab(), "Pop");
    }
}