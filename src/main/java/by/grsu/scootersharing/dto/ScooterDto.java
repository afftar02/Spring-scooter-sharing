package by.grsu.scootersharing.dto;

import by.grsu.scootersharing.model.ScooterLocation;
import lombok.Data;

@Data
public class ScooterDto {
    public long scooterId;
    public ScooterLocation location;
    public double battery;
    public String modelName;
    public boolean isBooked = false;
}
