package by.grsu.scootersharing;

import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.model.ScooterLocation;
import by.grsu.scootersharing.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeDB {
    private static final List<Scooter> SCOOTERS = new ArrayList<>();
    private static final List<User> USERS = new ArrayList<>();

    //TODO:сделать хранение картинок на сервере
    static{
        SCOOTERS.add(new Scooter(1,new ScooterLocation("Grodno","GRSU building"),100,"img/xiaomi-scooter-1.png","Xiaomi model 123"));
        SCOOTERS.add(new Scooter(2,new ScooterLocation("LA","Venice beach"),85,"img/xiaomi-scooter-1.png","Xiaomi model 456"));
        SCOOTERS.add(new Scooter(3,new ScooterLocation("Sydney","Sydney bridge"),30,"img/xiaomi-scooter-1.png","Xiaomi model 789"));

        USERS.add(new User(1,"Nick","Johnson","nick@gmail.com","Nick123"));
        USERS.add(new User(2,"Kate","Rodrigues","kate@gmail.com","Kate456"));
        USERS.add(new User(3,"Alex","Kooper","alex@gmail.com","Alex789"));
    }

    public List<User> getUsers(){
        return USERS;
    }

    public void addUser(User user){
        USERS.add(user);
    }

    public void updateUser(User user){
        USERS.removeIf(x -> (x.getUserId() == user.getUserId()));
        USERS.add(user);
    }

    public void deleteUser(long id){
        USERS.removeIf(x -> (x.getUserId() == id));
    }

    public List<Scooter> getScooters(){
        return SCOOTERS;
    }

    public void updateScooter(Scooter scooter){
        SCOOTERS.removeIf(item -> item.getId() == scooter.getId());
        SCOOTERS.add(scooter);
    }
}
