package domain.validator;

import domain.Grade;
import exceptions.ValidationException;

public class GradeValidator implements Validator<Grade> {
    @Override
    public void validate(Grade entity) throws ValidationException {
        String msg = "";
        if(entity.getId() == null){
            msg += "Id-ul notei nu poate sa fie null\n";
        }
        if(entity.getTeacher().isEmpty()){
            msg += "Numele profesorului nu poate sa fie null\n";
        }
        if(entity.getValue() < 1 || entity.getValue() > 10){
            msg += "Nota trebuie sa fie intre 1 si 10 \n";
        }
        if(!msg.isEmpty()){
            throw new ValidationException(msg);
        }
    }
}
