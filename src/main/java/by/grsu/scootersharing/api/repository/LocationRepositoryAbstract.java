package by.grsu.scootersharing.api.repository;

import by.grsu.scootersharing.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepositoryAbstract extends JpaRepository<Location,Long> {
}
