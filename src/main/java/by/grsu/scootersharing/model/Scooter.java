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
@Entity(name = "Scooter")
@Table(name = "scooters")
public class Scooter {
    @Id
    @SequenceGenerator(
            name = "scooter_sequence",
            sequenceName = "scooter_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "scooter_sequence"
    )
    @Column(name = "id",updatable = false)
    private long id;

    @ManyToOne()
    @JoinTable(name = "scooters_locations_join",
            joinColumns = {@JoinColumn(name = "scooter_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id", referencedColumnName = "id")})
    private ScooterLocation location;

    @Column(name = "battery",nullable = false)
    private double battery;

    @Column(name = "image",nullable = false)
    private String imageUrl;

    @Column(name = "model",nullable = false)
    private String modelName;

    @Column(name = "booked",nullable = false)
    private boolean isBooked = false;

    @ManyToOne()
    @JoinTable(name = "users_scooters_join",
            joinColumns = {@JoinColumn(name = "scooter_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private User user;

    public Scooter(ScooterLocation location, double battery, String imageUrl, String modelName) {
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
