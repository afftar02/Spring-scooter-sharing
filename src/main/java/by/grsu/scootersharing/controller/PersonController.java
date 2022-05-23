package by.grsu.scootersharing.controller;

import by.grsu.scootersharing.dto.PersonDto;
import by.grsu.scootersharing.model.Person;
import by.grsu.scootersharing.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("scooter-sharing/api/user")
public class PersonController {

    private final PersonService personService;
    private final ObjectMapper mapper;
    private static final Logger logger = Logger.getLogger(PersonController.class.toString());


    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
        this.mapper = new ObjectMapper();
    }

    @GetMapping
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/{personId}")
    public String getPersonById(@PathVariable String personId){
        try {
            long id = Long.parseLong(personId);
            return mapper.writeValueAsString(personService.getPersonById(id));
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/email")
    public String getPersonByEmail(@RequestParam String email){
        try {
            return mapper.writeValueAsString(personService.getPersonByEmail(email));
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public String addPerson(@RequestBody String requestJson) {
        try {
            PersonDto dto = mapper.readValue(requestJson, PersonDto.class);
            PersonDto response = personService.create(dto);
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public void update(@RequestBody String requestJson) {
        try {
            PersonDto dto = mapper.readValue(requestJson, PersonDto.class);
            personService.update(dto);
        } catch (JsonProcessingException e) {
            logger.info("Exception json processing " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public void delete(@RequestBody long id){
        personService.delete(id);
    }
}
