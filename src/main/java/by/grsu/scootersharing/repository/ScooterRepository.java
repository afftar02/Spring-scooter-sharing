package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.api.repository.LocationRepositoryAbstract;
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

    @Autowired
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

    public void updateScooter(Scooter scooter){
        locationRepositoryAbstract.getById(scooter.getLocation().getId()).setName(scooter.getLocation().getName());
        locationRepositoryAbstract.getById(scooter.getLocation().getId()).setDescription(scooter.getLocation().getDescription());
        locationRepositoryAbstract.flush();

        scooterRepositoryAbstract.getById(scooter.getId()).setLocation(scooter.getLocation());
        scooterRepositoryAbstract.getById(scooter.getId()).setBattery(scooter.getBattery());
        scooterRepositoryAbstract.getById(scooter.getId()).setImageUrl(scooter.getImageUrl());
        scooterRepositoryAbstract.getById(scooter.getId()).setModelName(scooter.getModelName());
        scooterRepositoryAbstract.getById(scooter.getId()).setBooked(scooter.isBooked());
        scooterRepositoryAbstract.flush();
    }

    public void delete(long id){
        scooterRepositoryAbstract.deleteById(id);
    }
}
