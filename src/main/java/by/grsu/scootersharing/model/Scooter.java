package by.grsu.scootersharing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
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

    @Column(nullable = false)
    private int timeLeft;//in minutes

    @Column
    private Date dateTimeEnd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return Double.compare(scooter.battery, battery) == 0 && isBooked == scooter.isBooked && timeLeft == scooter.timeLeft && dateTimeEnd == scooter.dateTimeEnd && id.equals(scooter.id) && location.equals(scooter.location) && imageUrl.equals(scooter.imageUrl) && modelName.equals(scooter.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, battery, imageUrl, modelName, isBooked, timeLeft, dateTimeEnd);
    }
}
