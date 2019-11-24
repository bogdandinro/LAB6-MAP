package domain.validator;

import domain.Student;
import exceptions.ValidationException;

public class StudentValidator implements Validator<Student> {

    @Override
    public void validate(Student entity) throws ValidationException {
        String msg = "";
        if(entity.getId() == null){
            msg += "Id-ul nu trebuie sa fie null \n";
        }
        if(entity.getId() <= 0){
            msg += "ID-ul trebuie sa fie un numar pozitiv  \n";
        }
        if (entity.getFirstName().isEmpty()) {
            msg += "Numele nu trebuie sa fie vid \n";
        }
        if (!Character.isUpperCase(entity.getFirstName().charAt(0))) {
            msg += "Numele nu trebuie sa fie vid \n";
        }
        if (entity.getSecondName().isEmpty()) {
            msg += "Prenumele nu trebuie sa fie vid \n";
        }
        if (!Character.isUpperCase(entity.getSecondName().charAt(0))) {
            msg += "Prenumele nu trebuie sa fie vid";
        }
        if (!entity.getEmail().endsWith("@scs.ubbcluj.ro")) {
            msg += "Email invalid ";
        }
        if (entity.getGroup() / 100 != 2 || (entity.getGroup() / 10) % 10 > 3) {
            msg += "Invalid group";
        }
        if (entity.getTeacherLab().isEmpty()) {
            msg += "Numele profesorului nu trebuie sa fie null ";
        }
        if (!Character.isUpperCase(entity.getTeacherLab().charAt(0))) {
            msg += " Numele profesorului nu poate  sa fie null ";
        }
        if (!msg.isEmpty()) {
            throw new ValidationException(msg);
        }
    }
}
