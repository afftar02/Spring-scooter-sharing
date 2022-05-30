package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.api.repository.LocationRepositoryAbstract;
import by.grsu.scootersharing.api.repository.PersonRepositoryAbstract;
import by.grsu.scootersharing.api.repository.ScooterRepositoryAbstract;
import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScooterRepository {
    private final ScooterRepositoryAbstract scooterRepositoryAbstract;
    private final LocationRepositoryAbstract locationRepositoryAbstract;

    public ScooterRepository(ScooterRepositoryAbstract scooterRepositoryAbstract, LocationRepositoryAbstract locationRepositoryAbstract){
        this.scooterRepositoryAbstract = scooterRepositoryAbstract;
        this.locationRepositoryAbstract = locationRepositoryAbstract;
    }

    public List<Scooter> getScooters(){
        return scooterRepositoryAbstract.findAll();
    }

    public Scooter getScooterById(long id){
        return scooterRepositoryAbstract.getById(id);
    }

    public Scooter create(Scooter scooter){
        Location locationWithId = locationRepositoryAbstract.save(scooter.getLocation());
        scooter.setLocation(locationWithId);
        return scooterRepositoryAbstract.save(scooter);
    }

    public Scooter updateScooter(Scooter scooter){
        if(scooter.getLocation() != null){
            if(scooter.getLocation().getId() == null){
                locationRepositoryAbstract.saveAndFlush(scooter.getLocation());
            }
            scooterRepositoryAbstract.getById(scooter.getId()).setLocation(scooter.getLocation());
        }
        if(scooter.getBattery() != 0){
            scooterRepositoryAbstract.getById(scooter.getId()).setBattery(scooter.getBattery());
        }
        if(scooter.getImageUrl() != null){
            scooterRepositoryAbstract.getById(scooter.getId()).setImageUrl(scooter.getImageUrl());
        }
        if(scooter.getModelName() != null){
            scooterRepositoryAbstract.getById(scooter.getId()).setModelName(scooter.getModelName());
        }
        if(scooter.isBooked() != scooterRepositoryAbstract.getById(scooter.getId()).isBooked()){
            scooterRepositoryAbstract.getById(scooter.getId()).setBooked(scooter.isBooked());
        }
        scooterRepositoryAbstract.getById(scooter.getId()).setTimeLeft(scooter.getTimeLeft());
        if(scooter.getDateTimeEnd() != null){
            scooterRepositoryAbstract.getById(scooter.getId()).setDateTimeEnd(scooter.getDateTimeEnd());
        }
        scooterRepositoryAbstract.flush();
        return scooterRepositoryAbstract.getById(scooter.getId());
    }

    public void delete(long id){
        scooterRepositoryAbstract.deleteById(id);
    }
}
