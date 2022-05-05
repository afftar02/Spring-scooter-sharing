package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.FakeDB;
import by.grsu.scootersharing.model.Scooter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScooterRepository {
    private final FakeDB database;

    @Autowired
    public ScooterRepository(FakeDB database){
        this.database = database;
    }

    public List<Scooter> getScooters(){
        return database.getScooters();
    }

    public void updateScooter(Scooter scooter){
        database.updateScooter(scooter);
    }
}
