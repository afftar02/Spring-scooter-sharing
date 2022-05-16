package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.api.repository.UserRepositoryAbstract;
import by.grsu.scootersharing.model.Person;
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

    public List<Person> getUsers(){
        return userRepositoryAbstract.findAll();
    }

    public Person getUserById(long id){
        return userRepositoryAbstract.getById(id);
    }

    public Person create(Person person){
        return userRepositoryAbstract.save(person);
    }

    public void update(Person person){
        userRepositoryAbstract.getById(person.getId()).setFirstName(person.getFirstName());
        userRepositoryAbstract.getById(person.getId()).setSecondName(person.getSecondName());
        userRepositoryAbstract.getById(person.getId()).setEmail(person.getEmail());
        userRepositoryAbstract.getById(person.getId()).setPassword(person.getPassword());
        userRepositoryAbstract.flush();
    }

    public void delete(long id){
        userRepositoryAbstract.deleteById(id);
    }
}
