package ch.ilv5.demoapp.model;

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
public class Places {
    @Id
    @GeneratedValue
    private Long id;

    @jakarta.validation.constraints.Size(max = 50)
    @NotEmpty
    @Column(nullable = false)
    private String plz;

    @jakarta.validation.constraints.Size(max = 20)
    @NotEmpty
    @Column(nullable = false)
    private String ortsname;

    @Column(nullable = false)
    @jakarta.validation.constraints.Size(max = 50)
    @NotEmpty
    private String strasse;

    @Column(nullable = false)
    private int strassennummer;
}
