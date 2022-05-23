package by.grsu.scootersharing.service;

import by.grsu.scootersharing.dto.PersonDto;
import by.grsu.scootersharing.model.Person;
import by.grsu.scootersharing.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.getPersonByEmail(email);
        if(person == null){
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("User"));
        return new org.springframework.security.core.userdetails.User(person.getEmail(),person.getPassword(), authorities);
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
