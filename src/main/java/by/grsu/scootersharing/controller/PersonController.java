package by.grsu.scootersharing.controller;

import by.grsu.scootersharing.dto.PersonDto;
import by.grsu.scootersharing.model.Person;
import by.grsu.scootersharing.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("scooter-sharing/api/user")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok().body(personService.getPersons());
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable String personId){
        long id = Long.parseLong(personId);
        return ResponseEntity.ok().body(personService.getPersonById(id));
    }

    @GetMapping("/email")
    public ResponseEntity<PersonDto> getPersonByEmail(@RequestParam String email){
        return ResponseEntity.ok().body(personService.getPersonByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto dto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("scooter-sharing/api/user/create").toUriString());
        return ResponseEntity.created(uri).body(personService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto dto) {
        return ResponseEntity.ok().body(personService.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
