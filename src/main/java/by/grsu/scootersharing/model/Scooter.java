package by.grsu.scootersharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Scooter {
    private UUID scooterId;
    private ScooterLocation location;
    private double battery;
    private String imageURL;
    private String modelName;
    private boolean isBooked = false;

    public Scooter(UUID scooterId, ScooterLocation location, double battery, String imageURL, String modelName) {
        this.scooterId = scooterId;
        this.location = location;
        this.battery = battery;
        this.imageURL = imageURL;
        this.modelName = modelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return Double.compare(scooter.battery, battery) == 0 && isBooked == scooter.isBooked && scooterId.equals(scooter.scooterId) && location.equals(scooter.location) && imageURL.equals(scooter.imageURL) && modelName.equals(scooter.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scooterId, location, battery, imageURL, modelName, isBooked);
    }
}
