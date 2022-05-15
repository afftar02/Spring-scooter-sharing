package by.grsu.scootersharing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "second_name",nullable = false)
    private String secondName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @OneToMany()
    @JoinTable(name = "scooters",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "scooters_id", referencedColumnName = "id")})
    private List<Scooter> scooters;

    public User(User user, long id){
        this.userId = id;
        this.firstName = user.firstName;
        this.secondName = user.secondName;
        this.email = user.email;
        this.password = user.password;
    }

    public User(long userId, String firstName, String secondName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && firstName.equals(user.firstName) && secondName.equals(user.secondName) && email.equals(user.email) && Objects.equals(scooters, user.scooters) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, secondName, email, scooters, password);
    }
}
