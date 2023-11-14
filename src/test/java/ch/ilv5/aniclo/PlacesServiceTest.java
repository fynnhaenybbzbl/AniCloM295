package ch.ilv5.aniclo;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Places;
import ch.ilv5.aniclo.repository.PlacesRepository;
import ch.ilv5.aniclo.service.PlacesService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlacesServiceTest {
    private PlacesService placesService;

    @Mock
    private PlacesRepository placesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        placesService = new PlacesService(placesRepository);
    }

    @Test
    public void testGetAll() {
        List<Places> placesList = new ArrayList<>();
        placesList.add(new Places(1234, "Test", "Teststrasse", 1));
        placesList.add(new Places(5678, "Test2", "Teststrasse2", 2));

        when(placesRepository.findAll()).thenReturn(placesList);

        List<Places> result = placesService.getAll();

        assertEquals(2, result.size());
        assertEquals(1234, result.get(0).getPlz());
        assertEquals("Test", result.get(0).getOrtsname());
        assertEquals("Teststrasse", result.get(0).getStrasse());
        assertEquals(1, result.get(0).getStrassennummer());
        assertEquals(5678, result.get(1).getPlz());
        assertEquals("Test2", result.get(1).getOrtsname());
        assertEquals("Teststrasse2", result.get(1).getStrasse());
        assertEquals(2, result.get(1).getStrassennummer());
    }

    @Test
    public void testGetById() {
        Places place = new Places(1234, "Test", "Teststrasse", 1);

        when(placesRepository.findById(1L)).thenReturn(Optional.of(place));

        Places result = placesService.getById(1L);

        assertEquals(1234, result.getPlz());
        assertEquals("Test", result.getOrtsname());
        assertEquals("Teststrasse", result.getStrasse());
        assertEquals(1, result.getStrassennummer());
    }

    @Test
    public void testGetByIdNotFound() {
        when(placesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> placesService.getById(1L));
    }

    @Test
    public void testDeletePlaceById() {
        MessageResponse expectedResponse = new MessageResponse("Place 1 deleted");

        MessageResponse result = placesService.deletePlaceById(1L);

        assertEquals(expectedResponse.getMessage(), result.getMessage());
        verify(placesRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testInsertPlace() {
        Places place = new Places(1234, "Test", "Teststrasse", 1);

        when(placesRepository.save(any(Places.class))).thenReturn(place);

        Places result = placesService.insertPlace(place);

        assertEquals(1234, result.getPlz());
        assertEquals("Test", result.getOrtsname());
        assertEquals("Teststrasse", result.getStrasse());
        assertEquals(1, result.getStrassennummer());
    }

    @Test
    public void testUpdatePlaceById() {
        Places place = new Places(1234, "Test", "Teststrasse", 1);
        Places updatedPlace = new Places(5678, "Test2", "Teststrasse2", 2);

        when(placesRepository.findById(1L)).thenReturn(Optional.of(place));
        when(placesRepository.save(any(Places.class))).thenReturn(updatedPlace);

        Places result = placesService.updatePlaceById(updatedPlace, 1L);

        assertEquals(5678, result.getPlz());
        assertEquals("Test2", result.getOrtsname());
        assertEquals("Teststrasse2", result.getStrasse());
        assertEquals(2, result.getStrassennummer());
    }
}