package by.grsu.scootersharing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class Scooter {
    private long scooterId;
    private ScooterLocation location;
    private double battery;
    private String modelName;
    private boolean isBooked = false;

    public Scooter(long scooterId, ScooterLocation location, double battery, String modelName) {
        this.scooterId = scooterId;
        this.location = location;
        this.battery = battery;
        this.modelName = modelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return scooterId == scooter.scooterId && Double.compare(scooter.battery, battery) == 0 && isBooked == scooter.isBooked && location.equals(scooter.location) && modelName.equals(scooter.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scooterId, location, battery, modelName, isBooked);
    }
}
