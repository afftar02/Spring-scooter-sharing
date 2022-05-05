package by.grsu.scootersharing.service;

import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.repository.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService {

    private final ScooterRepository scooterRepository;

    @Autowired
    public ScooterService(ScooterRepository scooterRepository){
        this.scooterRepository = scooterRepository;
    }

    public List<Scooter> getScooters(){
        return scooterRepository.getScooters();
    }

    public void updateScooter(Scooter scooter){
        scooterRepository.updateScooter(scooter);
    }
}
