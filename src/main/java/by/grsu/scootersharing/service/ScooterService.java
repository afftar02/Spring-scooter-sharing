package by.grsu.scootersharing.service;

import by.grsu.scootersharing.dto.ScooterDto;
import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.repository.ScooterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService {

    private final ScooterRepository scooterRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ScooterService(ScooterRepository scooterRepository){
        this.scooterRepository = scooterRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<Scooter> getScooters(){
        return scooterRepository.getScooters();
    }

    public ScooterDto getScooterById(long id){
        Scooter response = scooterRepository.getScooterById(id);
        return modelMapper.map(response, ScooterDto.class);
    }

    public ScooterDto create(ScooterDto dto){
        Scooter scooter = modelMapper.map(dto,Scooter.class);
        Scooter response = scooterRepository.create(scooter);
        return modelMapper.map(response,ScooterDto.class);
    }

    public void updateScooter(ScooterDto dto){
        Scooter scooter = modelMapper.map(dto,Scooter.class);
        scooterRepository.updateScooter(scooter);
    }

    public void delete(long id){
        scooterRepository.delete(id);
    }
}
