package by.grsu.scootersharing.service;

import by.grsu.scootersharing.dto.PersonDto;
import by.grsu.scootersharing.model.Person;
import by.grsu.scootersharing.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
        modelMapper = new ModelMapper();
    }

    public List<Person> getPersons(){
        return personRepository.getPersons();
    }

    public PersonDto getPersonById(long id){
        Person response = personRepository.getPersonById(id);
        return modelMapper.map(response, PersonDto.class);
    }

    public PersonDto getPersonByEmail(String email){
        Person response = personRepository.getPersonByEmail(email);
        return modelMapper.map(response, PersonDto.class);
    }

    public PersonDto create(PersonDto dto){
        Person person = modelMapper.map(dto, Person.class);
        Person response = personRepository.create(person);
        return modelMapper.map(response, PersonDto.class);
    }

    public PersonDto update(PersonDto dto){
        Person person = modelMapper.map(dto, Person.class);
        Person response = personRepository.update(person);
        return modelMapper.map(response, PersonDto.class);
    }

    public void delete(long id){
        personRepository.delete(id);
    }
}
