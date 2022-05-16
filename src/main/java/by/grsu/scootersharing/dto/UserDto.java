package by.grsu.scootersharing.dto;

import by.grsu.scootersharing.model.Scooter;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    public long id;
    public String firstName;
    public String secondName;
    public String email;
    public String password;
    public List<Scooter> scooters;
}
