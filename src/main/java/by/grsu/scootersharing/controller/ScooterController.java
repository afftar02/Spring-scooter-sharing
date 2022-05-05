package by.grsu.scootersharing.controller;

import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.service.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("scooter-sharing/api/scooters")
public class ScooterController {

    private final ScooterService scooterService;

    @Autowired
    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
    }

    @GetMapping
    public List<Scooter> getScooters(){
        return scooterService.getScooters();
    }

    @PutMapping("/{userId}/scooter")
    public String updateScooter(@PathVariable String userId, @RequestParam String id){
//        scooterService.updateScooter(scooter);
        System.out.println(userId + " " + id);
        return "put is working";
        //TODO: доделать обновление самоката через передачу в requestparam обьекта scooter
    }
}
