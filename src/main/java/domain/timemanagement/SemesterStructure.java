package domain.timemanagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SemesterStructure {
    private LocalDate startSemester;
    private LocalDate startHoliday;
    private LocalDate endHoliday;
    private LocalDate endSemester;

    public SemesterStructure(LocalDate startSemester, LocalDate startHoliday, LocalDate endHoliday, LocalDate endSemester) {
        this.startSemester = startSemester;
        this.startHoliday = startHoliday;
        this.endHoliday = endHoliday;
        this.endSemester = endSemester;
    }

    public LocalDate getStartSemester() {
        return startSemester;
    }

    public LocalDate getEndSemester() {
        return endSemester;
    }

    public int getCurrentWeek(LocalDate date) {
        if ((date.isBefore(endHoliday) && date.isAfter(startHoliday)) || date.isBefore(startSemester) || date.isAfter(endSemester)) {
            return -1;
        }
        //first part (without holiday)
        if (date.isBefore(startHoliday) && date.isAfter(startSemester)) {
            return (int) ChronoUnit.WEEKS.between(startSemester, date) + 1;
            //return (int) ChronoUnit.WEEKS.between(date, startSemester) + 1;
        }
        //considering holiday's weeks
        if (date.isAfter(endHoliday) && date.isBefore(endSemester)) {
            int holidayWeeks = (int) ChronoUnit.WEEKS.between(startHoliday, endHoliday) + 1;
            return (int) ChronoUnit.WEEKS.between(startSemester, date) + holidayWeeks + 1;
        }
        return 0;
    }
}
