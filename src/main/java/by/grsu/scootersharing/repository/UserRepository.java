package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.api.repository.UserRepositoryAbstract;
import by.grsu.scootersharing.dto.UserDto;
import by.grsu.scootersharing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final UserRepositoryAbstract userRepositoryAbstract;

    @Autowired
    public UserRepository(UserRepositoryAbstract userRepositoryAbstract) {
        this.userRepositoryAbstract = userRepositoryAbstract;
    }

    public List<User> getUsers(){
        return userRepositoryAbstract.findAll();
    }

    public User getUserById(long id){
        return userRepositoryAbstract.getById(id);
    }

    public User create(User user){
        return userRepositoryAbstract.save(user);
    }

    public void update(User user){
        userRepositoryAbstract.getById(user.getId()).setFirstName(user.getFirstName());
        userRepositoryAbstract.getById(user.getId()).setSecondName(user.getSecondName());
        userRepositoryAbstract.getById(user.getId()).setEmail(user.getEmail());
        userRepositoryAbstract.getById(user.getId()).setPassword(user.getPassword());
        userRepositoryAbstract.getById(user.getId()).setScooters(user.getScooters());
        userRepositoryAbstract.flush();
    }

    public void delete(long id){
        userRepositoryAbstract.deleteById(id);
    }
}
