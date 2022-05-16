package by.grsu.scootersharing.api.repository;

import by.grsu.scootersharing.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryAbstract extends JpaRepository<Person,Long> {

//    public List<User> getUsers(){
//        return database.getUsers();
//    }
//
//    public User create(User user){
//        User newUser = new User(user);
//        database.addUser(newUser);
//        return newUser;
//    }
//
//    public void update(User user){
//        database.updateUser(user);
//    }
//
//    public void delete(long id){
//        database.deleteUser(id);
//    }
//
//    private boolean isIdExists(long id) {
//        for (var user : database.getUsers()) {
//            if (user.getId() == id) {
//                return true;
//            }
//        }
//        return false;
//    }
}
