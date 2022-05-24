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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder){
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        modelMapper = new ModelMapper();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.getPersonByUsername(username);
        if(person == null){
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("User"));
        return new org.springframework.security.core.userdetails.User(person.getUsername(),person.getPassword(), authorities);
    }

    public List<Person> getPersons(){
        return personRepository.getPersons();
    }

    public PersonDto getPersonById(long id){
        Person response = personRepository.getPersonById(id);
        return modelMapper.map(response, PersonDto.class);
    }

    public PersonDto getPersonByUsername(String username){
        Person response = personRepository.getPersonByUsername(username);
        return modelMapper.map(response, PersonDto.class);
    }

    public PersonDto create(PersonDto dto){
        Person person = modelMapper.map(dto, Person.class);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Person response = personRepository.create(person);
        return modelMapper.map(response, PersonDto.class);
    }

    public PersonDto update(PersonDto dto){
        Person person = modelMapper.map(dto, Person.class);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Person response = personRepository.update(person);
        return modelMapper.map(response, PersonDto.class);
    }

    public void delete(long id){
        personRepository.delete(id);
    }

}
