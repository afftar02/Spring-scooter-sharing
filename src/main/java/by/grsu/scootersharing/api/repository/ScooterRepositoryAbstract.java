package by.grsu.scootersharing.api.repository;

import by.grsu.scootersharing.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepositoryAbstract extends JpaRepository<Scooter,Long> {

//    public List<Scooter> getScooters(){
//        return database.getScooters();
//    }
//
//    public void updateScooter(Scooter scooter){
//        database.updateScooter(scooter);
//    }
}
