package ch.ilv5.aniclo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Entity
@Validated
public class Colour {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @jakarta.validation.constraints.Size(max = 15)
    @NotEmpty
    private String farbe;

}
