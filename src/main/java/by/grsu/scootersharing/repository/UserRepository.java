package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.FakeDB;
import by.grsu.scootersharing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
