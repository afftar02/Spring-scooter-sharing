package by.grsu.scootersharing.dto;

import by.grsu.scootersharing.model.Scooter;
import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    public long id;
    public String firstName;
    public String secondName;
    public String email;
    public String password;
}
