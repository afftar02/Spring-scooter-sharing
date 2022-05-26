//package by.grsu.scootersharing.service;
//
//import by.grsu.scootersharing.model.Scooter;
//import by.grsu.scootersharing.repository.ScooterRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//@Service
//@Slf4j
//public class TimerService {
//    //    private static final List<Scooter> scooters = new ArrayList<>();
//    private final ScooterRepository scooterRepository;
//    private final Timer scooterTimer;
//    private final Timer savingPeriodTimer;
//
//    @Autowired
//    public TimerService(ScooterRepository scooterRepository) {
//        this.scooterRepository = scooterRepository;
//        this.savingPeriodTimer = new Timer(5000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        this.scooterTimer =  new Timer();
//    }
//
//    public void startTimer(long id, boolean isMinTimeUnit, int duration) {
//        duration = isMinTimeUnit ? duration * 60000 : duration * 3600000;
//        Scooter currentScooter = scooterRepository.getScooterById(id);
//        currentScooter.setTimeLeft();
//        scooterRepository.updateScooter()
//    }
//
//    public void saveCurrentTime(long id,int)
//
//    public void stopTimer(long id) {
//
//    }
//}
