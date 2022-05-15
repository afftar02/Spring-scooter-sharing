package by.grsu.scootersharing.dto;

import by.grsu.scootersharing.model.ScooterLocation;
import lombok.Data;

@Data
public class ScooterDto {
    public long id;
    public ScooterLocation location;
    public double battery;
    public String imageUrl;
    public String modelName;
    public String userId;
    public boolean isBooked = false;
}
