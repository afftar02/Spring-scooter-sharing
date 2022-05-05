package by.grsu.scootersharing;

import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.model.ScooterLocation;
import by.grsu.scootersharing.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeDB {
    private static final List<Scooter> SCOOTERS = new ArrayList<>();
    private static final List<User> USERS = new ArrayList<>();

    //TODO:сделать хранение картинок на сервере
    static{
        SCOOTERS.add(new Scooter(UUID.randomUUID(),new ScooterLocation("Grodno","GRSU building"),100,"","Xiaomi model 228"));
        SCOOTERS.add(new Scooter(UUID.randomUUID(),new ScooterLocation("LA","Venice beach"),85,"","Xiaomi model 228"));
        SCOOTERS.add(new Scooter(UUID.randomUUID(),new ScooterLocation("Sydney","Sydney bridge"),30,"","Xiaomi model 228"));

        USERS.add(new User(UUID.randomUUID(),"Nick"));
        USERS.add(new User(UUID.randomUUID(),"Kate"));
        USERS.add(new User(UUID.randomUUID(),"Alex"));
    }

    public List<Scooter> getScooters(){
        return SCOOTERS;
    }

    public List<User> getUsers(){
        return USERS;
    }

    public void updateScooter(Scooter scooter){
        SCOOTERS.removeIf(item -> item.getScooterId() == scooter.getScooterId());
        SCOOTERS.add(scooter);
    }
}
