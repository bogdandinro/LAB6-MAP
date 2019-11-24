package service;

import domain.Lab;
import domain.timemanagement.YearStructure;
import repository.LabFileRepository;

public class LabService {
    private LabFileRepository labFileRepository;
    private YearStructure yearStructure;

    public LabService(LabFileRepository labFileRepository, YearStructure yearStructure) {
        this.labFileRepository = labFileRepository;
        this.yearStructure = yearStructure;
    }

    public Lab search(Long id){
        return labFileRepository.findOne(id);
    }

    public Iterable<Lab> findAll(){
        return labFileRepository.findAll();
    }

    public Lab add(Long id, String description, int startWeek, int deadlineWeek){
        Lab lab = new Lab(id, description, startWeek, deadlineWeek);
        return labFileRepository.save(lab);
    }

    public Lab delete(Long id){
        return labFileRepository.delete(id);
    }

    public Lab update(Long id, String description, int startWeek, int deadlineWeeke) {
        Lab lab = new Lab(id, description, startWeek, deadlineWeeke);
        Lab l = labFileRepository.findOne(lab.getId());
        if (l.getDeadlineWeek() < lab.getStartWeek()) {
            throw new IllegalArgumentException("Saptamana curenta nu poate sa fie mai mare de saptamana de deadline \n");
        }
        l.setDescription(lab.getDescription());
        l.setDeadlineWeek(lab.getDeadlineWeek());
        return labFileRepository.update(l);
    }
}
