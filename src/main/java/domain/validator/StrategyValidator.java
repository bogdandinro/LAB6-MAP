package domain.validator;

import domain.Entity;

public class StrategyValidator {
    private Validator<Entity> validator;

    public StrategyValidator(Validator<Entity> validator) {
        this.validator = validator;
    }

    public void executeValidation(Entity entity){
        validator.validate(entity);
    }
}
