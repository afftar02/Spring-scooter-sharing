package by.grsu.scootersharing.controller;

import by.grsu.scootersharing.dto.UserDto;
import by.grsu.scootersharing.model.User;
import by.grsu.scootersharing.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("scooter-sharing/api/user")
public class UserController {

    private final UserService userService;
    private final ObjectMapper mapper;
    private static final Logger logger = Logger.getLogger(UserController.class.toString());


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.mapper = new ObjectMapper();
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable String userId){
        try {
            long id = Long.parseLong(userId);
            return mapper.writeValueAsString(userService.getUserById(id));
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public String addUser(@RequestBody String requestJson) {
        try {
            UserDto dto = mapper.readValue(requestJson, UserDto.class);
            UserDto response = userService.create(dto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public void update(@RequestBody String requestJson) {
        try {
            UserDto dto = mapper.readValue(requestJson, UserDto.class);
            userService.update(dto);
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public void delete(@RequestBody long id){
        userService.delete(id);
    }
}
