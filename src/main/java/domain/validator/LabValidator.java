package domain.validator;

import domain.Lab;
import exceptions.ValidationException;

public class LabValidator implements Validator<Lab> {
    @Override
    public void validate(Lab entity) throws ValidationException {
        String msg = "";
        if(entity.getId() < 0){
            msg += "Id-ul laboratorului trebuie sa fie un nr pozitiv \n";
        }
        if(entity.getDescription().isEmpty()){
            msg += "Descrierea nu poate sa fie vida \n";
        }
        if(entity.getDeadlineWeek() < 1 || entity.getDeadlineWeek() > 14){
            msg += "Saptamana de deadline trebuie sa fie intre 1 si 14\n";
        }
        if(entity.getStartWeek() < 1 || entity.getStartWeek() > 14){
            msg += "Saptamana de inceput trebuie sa fie intre 1 si 14\n";
        }
        if(!msg.isEmpty()){
            throw new ValidationException(msg);
        }
    }
}
