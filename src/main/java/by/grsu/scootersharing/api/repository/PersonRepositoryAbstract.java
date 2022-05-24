package by.grsu.scootersharing.api.repository;

import by.grsu.scootersharing.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryAbstract extends JpaRepository<Person,Long> {
    Person getByUsername(String username);

//    private boolean isIdExists(long id) {
//        for (var user : database.getUsers()) {
//            if (user.getId() == id) {
//                return true;
//            }
//        }
//        return false;
//    }
}
