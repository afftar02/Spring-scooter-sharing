package by.grsu.scootersharing.repository;

import by.grsu.scootersharing.api.repository.PersonRepositoryAbstract;
import by.grsu.scootersharing.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {
    private final PersonRepositoryAbstract personRepositoryAbstract;

    @Autowired
    public PersonRepository(PersonRepositoryAbstract personRepositoryAbstract) {
        this.personRepositoryAbstract = personRepositoryAbstract;
    }

    public List<Person> getPersons(){
        return personRepositoryAbstract.findAll();
    }

    public Person getPersonById(long id){
        return personRepositoryAbstract.getById(id);
    }

    public Person getPersonByEmail(String email){
        return personRepositoryAbstract.getByEmail(email);
    }

    public Person create(Person person){
        return personRepositoryAbstract.save(person);
    }

    public Person update(Person person){
        if(person.getFirstName() != null){
            personRepositoryAbstract.getById(person.getId()).setFirstName(person.getFirstName());
        }
        if(person.getSecondName() != null){
            personRepositoryAbstract.getById(person.getId()).setSecondName(person.getSecondName());
        }
        if(person.getEmail() != null){
            personRepositoryAbstract.getById(person.getId()).setEmail(person.getEmail());
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
