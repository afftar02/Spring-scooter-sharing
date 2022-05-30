package by.grsu.scootersharing.service;

import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.repository.ScooterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Service
@Slf4j
public class TimerService {
    private final List<Scooter> scooters;
    private final ScooterRepository scooterRepository;
    private final Timer scooterTimer;
    private final Timer savingPeriodTimer;

    public TimerService(ScooterRepository scooterRepository) {
        this.scooterRepository = scooterRepository;
        this.scooters = scooterRepository.getScooters();
        this.savingPeriodTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.scooterTimer =  null;
    }

    public void startTimer(long id, int duration) {
        Scooter currentScooter = scooterRepository.getScooterById(id);
        currentScooter.setTimeLeft(duration);
        scooterRepository.updateScooter(currentScooter);
    }

    public void saveCurrentTime(long id,int duration){

    }

    public void stopTimer(long id) {

    }
}
