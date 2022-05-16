package by.grsu.scootersharing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
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
    @Column(updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private double battery;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private boolean isBooked = false;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Scooter(Location location, double battery, String imageUrl, String modelName) {
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
