package by.grsu.scootersharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class User {
    private long userId;
    private String firstName;
    private String secondName;
    private String email;
    private String password;

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
        return userId == user.userId && firstName.equals(user.firstName) && secondName.equals(user.secondName) && email.equals(user.email) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, secondName, email, password);
    }
}
