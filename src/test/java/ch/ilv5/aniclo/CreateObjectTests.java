package ch.ilv5.aniclo;

import ch.ilv5.aniclo.model.Places;
import ch.ilv5.aniclo.repository.PlacesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateObjectTests {

    @Autowired
    private PlacesRepository placesRepository;

    @Test
    public void testRead() {
        assertNotNull(this.placesRepository.findAll());
    }

    @Test
    public void testCreate() {
        // Erstelle ein neues Objekt
        Places place = new Places(4107, "Ettingen", "Therwilerstrasse", 7);
        this.placesRepository.saveAndFlush(place);

        assertFalse(placesRepository.findById(place.getId()).isPresent());
    }

    @Test
    public void testDelete() {
        // Erstelle ein neues Objekt
        Places place = new Places(4107, "Ettingen", "Therwilerstrasse", 7);

        this.placesRepository.saveAndFlush(place);

        assertNotNull(this.placesRepository.findById(place.getId()));

        // Lösche das Objekt aus der Datenbank
        placesRepository.deleteById(place.getId());
        System.out.println("Objekt:" + place + "wurde gelöscht");

        // Überprüfe, ob das Objekt gelöscht wurde
        assertFalse(placesRepository.findById(place.getId()).isPresent());
    }

    @Test
    public void testUpdate() {
        // Erstelle ein neues Objekt
        Places place = new Places(4107, "Ettingen", "Therwilerstrasse", 7);

        this.placesRepository.saveAndFlush(place);

        assertNotNull(this.placesRepository.findById(place.getId()));

        place.setOrtsname("SOME RANDOM PLACE");

        this.placesRepository.saveAndFlush(place);

        var result = this.placesRepository.findById(place.getId());
        result.ifPresent(places -> assertEquals(place.getOrtsname(), places.getOrtsname()));

    }
}