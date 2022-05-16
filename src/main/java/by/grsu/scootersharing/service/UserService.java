package by.grsu.scootersharing.service;

import by.grsu.scootersharing.dto.UserDto;
import by.grsu.scootersharing.model.Person;
import by.grsu.scootersharing.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        modelMapper = new ModelMapper();
    }

    public List<Person> getUsers(){
        return userRepository.getUsers();
    }

    public UserDto getUserById(long id){
        Person response = userRepository.getUserById(id);
        return modelMapper.map(response,UserDto.class);
    }

    public UserDto create(UserDto dto){
        Person person = modelMapper.map(dto, Person.class);
        Person response = userRepository.create(person);
        return modelMapper.map(response,UserDto.class);
    }

    public void update(UserDto dto){
        Person person = modelMapper.map(dto, Person.class);
        userRepository.update(person);
    }

    public void delete(long id){
        userRepository.delete(id);
    }
}
