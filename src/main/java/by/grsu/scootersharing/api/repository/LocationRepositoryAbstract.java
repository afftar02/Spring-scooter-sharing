package by.grsu.scootersharing.api.repository;

import by.grsu.scootersharing.model.Scooter;
import by.grsu.scootersharing.model.ScooterLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepositoryAbstract extends JpaRepository<ScooterLocation,Long> {
}
