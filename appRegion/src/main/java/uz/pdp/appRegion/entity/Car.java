package uz.pdp.appRegion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer stateNumber;

    @Column(nullable = false)
    private Integer madeYear;

    @Column(nullable = false)
    private String type;

    @ManyToOne(optional = false)
    private Region region;

    @ManyToOne(optional = false)
    private Users users;
}
