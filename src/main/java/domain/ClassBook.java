package domain;

import java.time.LocalDate;

public class ClassBook {
    private Long id;
    private float value;
    private LocalDate date;
    private int deadlineWeek;
    private String feedback;

    public ClassBook(Long id, float value, LocalDate date, int deadlineWeek, String feedback) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.deadlineWeek = deadlineWeek;
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    public void setDeadlineWeek(int deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return id +
                ";" + value +
                ";" + date +
                ";" + deadlineWeek +
                ";" + feedback;
    }
}
