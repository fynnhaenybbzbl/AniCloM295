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
    public void testCreateReadDelete() {
        // Erstelle ein neues Objekt
        Places places = new Places(4107, "Ettingen", "Therwilerstrasse", 7);

        // Füge das Objekt zur Datenbank hinzu
        Places savedPlace = placesRepository.save(places);
        System.out.println("Speichert: " + places);

        // Lese das Objekt aus der Datenbank
        Places result = placesRepository.findById(savedPlace.getId()).orElse(null);
        // result.setAuthenticationType("OIDC");

        // Überprüfe, ob das Objekt korrekt gelesen wurde
        assertNotNull(result);
        assertEquals(places, result);


        // Lösche das Objekt aus der Datenbank
        placesRepository.deleteById(places.getId());
        System.out.println("Objekt:" + places + "wurde gelöscht");

        // Überprüfe, ob das Objekt gelöscht wurde
        assertFalse(placesRepository.findById(savedPlace.getId()).isPresent());
    }
}
