package by.grsu.scootersharing.controller;

import by.grsu.scootersharing.dto.ScooterDto;
import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.service.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("scooter-sharing/api/scooters")
public class ScooterController {

    private final ScooterService scooterService;

    @Autowired
    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
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

    @PostMapping("/create")
    public ResponseEntity<ScooterDto> addScooter(@RequestBody ScooterDto dto){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("scooter-sharing/api/scooters/create").toUriString());
        return ResponseEntity.created(uri).body(scooterService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ScooterDto> updateScooter(@RequestBody ScooterDto dto){
        return ResponseEntity.ok().body(scooterService.updateScooter(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        scooterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
