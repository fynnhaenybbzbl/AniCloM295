package ch.ilv5.aniclo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Entity
@Validated
@NoArgsConstructor
public class Places {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int plz;

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


    public Places(int plz, String ortsname, String strasse, int strassennummer) {
        this.plz = plz;
        this.ortsname = ortsname;
        this.strasse = strasse;
        this.strassennummer = strassennummer;
    }
}
