package ch.ilv5.aniclo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Entity
@Validated
public class Clothing {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @jakarta.validation.constraints.Size(max = 50)
    @NotEmpty
    private String name;

    @jakarta.validation.constraints.Size(max = 15)
    @NotEmpty
    @Column(nullable = false)
    private String marke;

    @Column(nullable = false)
    private int preis;

    @ManyToOne(optional = false)
    @JoinColumn(name = "colour_id")
    private Colour colour;

    @ManyToOne(optional = false)
    @JoinColumn(name = "places_id")
    private Places places;
}
