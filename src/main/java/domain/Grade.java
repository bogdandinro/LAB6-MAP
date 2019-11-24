package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Grade extends Entity<String> {
    private LocalDate date;
    private String teacher;
    private float value;
    private Long idLab;
    private Long idStudent;

    public Grade(String id, LocalDate date, String teacher, float value) {
        super.setId(id);
        String[] cva = id.split("_");
        idLab = Long.parseLong(cva[1]);
        idStudent = Long.parseLong(cva[0]);
        this.date = date;
        this.teacher = teacher;
        this.value = value;
    }

    public Long getIdLab() {
        return idLab;
    }

    public void setIdLab(Long idLab) {
        this.idLab = idLab;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        Grade grade = (Grade) o;
        return Objects.equals(super.getId(),((Grade) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate());
    }

    @Override
    public String toString() {
        return getId() +
                ";" + date +
                ";" + teacher +
                ";" + value;
    }
}
