package by.grsu.scootersharing.service;

import by.grsu.scootersharing.dto.UserDto;
import by.grsu.scootersharing.model.User;
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

    public List<User> getUsers(){
        return userRepository.getUsers();
    }

    public UserDto create(UserDto dto){
        User user = modelMapper.map(dto,User.class);
        User response = userRepository.create(user);
        return modelMapper.map(response,UserDto.class);
    }
}
