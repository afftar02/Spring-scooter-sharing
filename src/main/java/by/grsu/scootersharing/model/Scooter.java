package by.grsu.scootersharing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "scooters")
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne()
    @JoinTable(name = "locations",
            joinColumns = {@JoinColumn(name = "scooter_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id", referencedColumnName = "id")})
    private ScooterLocation location;

    @Column(name = "battery",nullable = false)
    private double battery;

    @Column(name = "image_url",nullable = false)
    private String imageUrl;

    @Column(name = "model",nullable = false)
    private String modelName;

    @Column(name = "booked",nullable = false)
    private boolean isBooked = false;

    public Scooter(long id, ScooterLocation location, double battery, String imageUrl, String modelName) {
        this.id = id;
        this.location = location;
        this.battery = battery;
        this.imageUrl = imageUrl;
        this.modelName = modelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return id == scooter.id && Double.compare(scooter.battery, battery) == 0 && isBooked == scooter.isBooked && location.equals(scooter.location) && imageUrl.equals(scooter.imageUrl) && modelName.equals(scooter.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, battery, imageUrl, modelName, isBooked);
    }
}
