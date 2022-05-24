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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false)
    private String username;

    @Column( nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "User";

    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Scooter> scooters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) && firstName.equals(person.firstName) && secondName.equals(person.secondName) && username.equals(person.username) && password.equals(person.password) && role.equals(person.role) && Objects.equals(scooters, person.scooters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, username, password, role, scooters);
    }
}
