package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.api.repository.PersonRepositoryAbstract;
import by.grsu.scootersharing.api.repository.RoleRepositoryAbstract;
import by.grsu.scootersharing.model.Person;
import by.grsu.scootersharing.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class PersonRepository {
    private final PersonRepositoryAbstract personRepositoryAbstract;
    private final RoleRepositoryAbstract roleRepositoryAbstract;

    @Autowired
    public PersonRepository(PersonRepositoryAbstract personRepositoryAbstract, RoleRepositoryAbstract roleRepositoryAbstract) {
        this.personRepositoryAbstract = personRepositoryAbstract;
        this.roleRepositoryAbstract = roleRepositoryAbstract;
    }

    public List<Person> getPersons(){
        return personRepositoryAbstract.findAll();
    }

    public Person getPersonById(long id){
        return personRepositoryAbstract.getById(id);
    }

    public Person getPersonByUsername(String username){
        return personRepositoryAbstract.getByUsername(username);
    }

    public Person create(Person person){
        Collection<Role> rolesWithId = new ArrayList<>();
        for (Role role: person.getRoles()) {
            if(roleRepositoryAbstract.getByName(role.getName()) != null) {
                rolesWithId.add(roleRepositoryAbstract.getByName(role.getName()));
            }
            else{
                rolesWithId.add(roleRepositoryAbstract.save(role));
            }
        }
        person.setRoles(rolesWithId);
        return personRepositoryAbstract.save(person);
    }

    public Person update(Person person){
        if(person.getFirstName() != null){
            personRepositoryAbstract.getById(person.getId()).setFirstName(person.getFirstName());
        }
        if(person.getSecondName() != null){
            personRepositoryAbstract.getById(person.getId()).setSecondName(person.getSecondName());
        }
        if(person.getUsername() != null){
            personRepositoryAbstract.getById(person.getId()).setUsername(person.getUsername());
        }
        if(person.getPassword() != null){
            personRepositoryAbstract.getById(person.getId()).setPassword(person.getPassword());
        }
        if(person.getScooters() != null){
            personRepositoryAbstract.getById(person.getId()).setScooters(person.getScooters());
        }
        personRepositoryAbstract.flush();
        return personRepositoryAbstract.getById(person.getId());
    }

    public void delete(long id){
        personRepositoryAbstract.deleteById(id);
    }
}
