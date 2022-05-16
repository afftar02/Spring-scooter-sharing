package by.grsu.scootersharing.controller;

import by.grsu.scootersharing.dto.ScooterDto;
import by.grsu.scootersharing.dto.UserDto;
import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.service.ScooterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("scooter-sharing/api/scooters")
public class ScooterController {

    private final ScooterService scooterService;
    private final ObjectMapper mapper;
    private static final Logger logger = Logger.getLogger(UserController.class.toString());

    @Autowired
    public ScooterController(ScooterService scooterService, ObjectMapper mapper) {
        this.scooterService = scooterService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Scooter> getScooters(){
        return scooterService.getScooters();
    }

    @GetMapping("/{scooterId}")
    public String getScooterById(@PathVariable String scooterId){
        try {
            long id = Long.parseLong(scooterId);
            return mapper.writeValueAsString(scooterService.getScooterById(id));
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public String addScooter(@RequestBody String requestJson){
        try {
            ScooterDto dto = mapper.readValue(requestJson, ScooterDto.class);
            ScooterDto response = scooterService.create(dto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public void updateScooter(@RequestBody String requestJson){
        try {
            ScooterDto dto = mapper.readValue(requestJson, ScooterDto.class);
            scooterService.updateScooter(dto);
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public void delete(@RequestBody long id){
        scooterService.delete(id);
    }
}
