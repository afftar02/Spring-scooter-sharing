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

    public Person create(Person person){
        return personRepositoryAbstract.save(person);
    }

    public void update(Person person){
        personRepositoryAbstract.getById(person.getId()).setFirstName(person.getFirstName());
        personRepositoryAbstract.getById(person.getId()).setSecondName(person.getSecondName());
        personRepositoryAbstract.getById(person.getId()).setEmail(person.getEmail());
        personRepositoryAbstract.getById(person.getId()).setPassword(person.getPassword());
        personRepositoryAbstract.flush();
    }

    public void delete(long id){
        personRepositoryAbstract.deleteById(id);
    }
}
