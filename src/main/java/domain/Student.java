package domain;

import java.util.Objects;

public class Student extends Entity<Long> {
    private String firstName;
    private String secondName;
    private int group;
    private String email;
    private String teacherLab;

    public Student(Long id, String firstName, String secondName, int group, String email, String teacherLab) {
        super.setId(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.group = group;
        this.email = email;
        this.teacherLab = teacherLab;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeacherLab() {
        return teacherLab;
    }

    public void setTeacherLab(String teacherLab) {
        this.teacherLab = teacherLab;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName());
    }

    @Override
    public String toString() {
        return super.getId() + ";"
                + firstName + ";"
                + secondName + ";"
                + group + ";"
                + email + ";"
                + teacherLab;
    }
}
