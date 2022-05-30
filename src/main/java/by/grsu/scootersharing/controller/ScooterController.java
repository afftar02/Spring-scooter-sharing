package by.grsu.scootersharing.controller;

import by.grsu.scootersharing.dto.ScooterDto;
import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.service.ScooterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("scooter-sharing/api/scooters")
public class ScooterController {

    private final ScooterService scooterService;
    private final ObjectMapper mapper;

    public ScooterController(ScooterService scooterService, ObjectMapper mapper) {
        this.scooterService = scooterService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<Scooter>> getScooters(){
        return ResponseEntity.ok().body(scooterService.getScooters());
    }

    @GetMapping("/{scooterId}")
    public ResponseEntity<ScooterDto> getScooterById(@PathVariable String scooterId){
        long id = Long.parseLong(scooterId);
        return ResponseEntity.ok().body(scooterService.getScooterById(id));
    }

    @PostMapping
    public ResponseEntity<ScooterDto> addScooter(@RequestBody String requestJson){
        try {
            ScooterDto dto = mapper.readValue(requestJson, ScooterDto.class);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("scooter-sharing/api/scooters/create").toUriString());
            return ResponseEntity.created(uri).body(scooterService.create(dto));
        } catch (JsonProcessingException e) {
            log.info("Exception json processing "+e.getMessage());
            throw new RuntimeException();
        }
    }

    @PutMapping
    public ResponseEntity<ScooterDto> updateScooter(@RequestBody String requestJson){
        try {
            ScooterDto dto = mapper.readValue(requestJson, ScooterDto.class);
            return ResponseEntity.ok().body(scooterService.updateScooter(dto));
        } catch (JsonProcessingException e) {
            log.info("Exception json processing "+e.getMessage());
            throw new RuntimeException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        scooterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
