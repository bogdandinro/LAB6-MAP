package repository;

import domain.Entity;
import domain.validator.Validator;
import exceptions.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements CrudRepository<ID, E> {
    private Validator<E> validator;
    private Map<ID, E> list;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        this.list = new HashMap<ID, E>();
    }

    /**
     *
     * @param id -the id of the entity to be returned
     * id must not be null
     * @return the entity with the specified id
     * or null - if there is no entity with the given id
     * @throws IllegalArgumentException
     * if id is null.
     */
    @Override
    public E findOne(ID id) {
        if(id == null){
            throw new IllegalArgumentException("ID-ul nu poate sa fie null\n");
        }
        if(list.containsKey(id)){
            return list.get(id);
        }
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        return list.values();
    }

    /**
     *
     * @param entity
     * entity must be not null
     * @return null- if the given entity is saved
     * otherwise returns the entity (id already exists)
     * @throws ValidationException
     * if the entity is not valid
     * @throws IllegalArgumentException
     * if the given entity is null. *
     */
    @Override
    public E save(E entity) throws ValidationException {
        if(entity == null){
            throw new IllegalArgumentException("Entitatea nu poate sa fie nula \n");
        }
        validator.validate(entity);
        if(list.containsKey(entity.getId())){
            return entity;
        }
        list.put(entity.getId(), entity);
        return null;
    }

    /**
     * removes the entity with the specified id
     * @param id
     * id must be not null
     * @return the removed entity or null if there is no entity with the
    given id
     * @throws IllegalArgumentException
     * if the given id is null.
     */
    @Override
    public E delete(ID id) {
        if(id == null){
            throw new IllegalArgumentException("ID-ul nu poate sa fie null \n");
        }
        if(list.containsKey(id)){
            return list.remove(id);
        }
        return null;
    }

    /**
     *
     * @param entity
     * entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity - (e.g id does not
    exist).
     * @throws IllegalArgumentException
     * if the given entity is null.
     * @throws ValidationException
     * if the entity is not valid.
     */
    @Override
    public E update(E entity) {
        if(entity == null){
            throw new IllegalArgumentException("Entitatea nu poate sa fie nula  \n");
        }
        if(!list.containsKey(entity.getId())){
            return entity;
        }
        validator.validate(entity);
        list.remove(entity.getId());
        list.put(entity.getId(), entity);
        return null;
    }
}
