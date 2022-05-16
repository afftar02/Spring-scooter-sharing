package by.grsu.scootersharing.dto;

import by.grsu.scootersharing.model.Location;
import by.grsu.scootersharing.model.Person;
import lombok.Data;

@Data
public class ScooterDto {
    public long id;
    public Location location;
    public double battery;
    public String imageUrl;
    public String modelName;
    public boolean booked = false;
    public Person person;
}
