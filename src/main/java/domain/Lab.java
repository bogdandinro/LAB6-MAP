package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Lab extends Entity<Long> {
    private String description;
    private int startWeek;
    private int deadlineWeek;

    public Lab(Long id, String description, int startWeek, int deadlineWeek) {
        super.setId(id);
        this.description = description;
        this.startWeek = startWeek;
        this.deadlineWeek = deadlineWeek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    public void setDeadlineWeek(int deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lab)) return false;
        Lab lab = (Lab) o;
        return Objects.equals(getId(), lab.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription());
    }

    @Override
    public String toString() {
        return super.getId() + ";" +
                description + ";" +
                startWeek + ";" +
                deadlineWeek;
    }

}
