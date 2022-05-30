package by.grsu.scootersharing.service;

import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.repository.ScooterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TimerService {
    private final ScooterRepository scooterRepository;

    public TimerService(ScooterRepository scooterRepository) {
        this.scooterRepository = scooterRepository;
    }

    public void setDateTimeEnd(long id, int duration) {
        Scooter currentScooter = scooterRepository.getScooterById(id);
        currentScooter.setTimeLeft(duration);
        Date timeEnd = new Date();
        timeEnd.setHours(timeEnd.getHours() + (duration / 60 >= 1 ? duration / 60 : 0));
        timeEnd.setMinutes(timeEnd.getMinutes() + (duration / 60 < 1 ? duration : 0));
        currentScooter.setDateTimeEnd(timeEnd);
        scooterRepository.updateScooter(currentScooter);
    }

    public void resetDateTimeEnd(long id) {
        Scooter currentScooter = scooterRepository.getScooterById(id);
        currentScooter.setTimeLeft(0);
        currentScooter.setDateTimeEnd(new Date());
        scooterRepository.updateScooter(currentScooter);
    }

    public void updateScootersTimeLeft(List<Scooter> scooters) {
        for (Scooter scooter : scooters) {
            saveCurrentTime(scooter);
        }
    }

    public void saveCurrentTime(Scooter currentScooter) {
//        Scooter currentScooter = scooterRepository.getScooterById(id);
        Date currentDateTime = new Date();
        if (currentScooter.getDateTimeEnd().getTime() > currentDateTime.getTime()) {
            long timeLeft = currentScooter.getDateTimeEnd().getTime() - currentDateTime.getTime();
            long minutesLeft = timeLeft / (60 * 1000);
            currentScooter.setTimeLeft((int) minutesLeft);
        } else {
            currentScooter.setTimeLeft(0);
        }
        scooterRepository.updateScooter(currentScooter);
    }

}
