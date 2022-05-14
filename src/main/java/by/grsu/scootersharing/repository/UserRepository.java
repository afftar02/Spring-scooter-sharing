package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.FakeDB;
import by.grsu.scootersharing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class UserRepository {
    private final FakeDB database;

    @Autowired
    public UserRepository(FakeDB database){
        this.database = database;
    }

    public List<User> getUsers(){
        return database.getUsers();
    }

    public User create(User user){
        long id;
        do {
            id = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        } while (isIdExists(id));
        User newUser = new User(user, id);
        database.addUser(newUser);
        return newUser;
    }

    private boolean isIdExists(long id) {
        for (var user : database.getUsers()) {
            if (user.getUserId() == id) {
                return true;
            }
        }
        return false;
    }
}
