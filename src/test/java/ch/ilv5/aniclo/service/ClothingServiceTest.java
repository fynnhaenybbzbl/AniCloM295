package ch.ilv5.aniclo.service;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Clothing;
import ch.ilv5.aniclo.model.Colour;
import ch.ilv5.aniclo.model.Places;
import ch.ilv5.aniclo.repository.ClothingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClothingServiceTest {
    @Mock
    private ClothingRepository clothingRepository;

    @InjectMocks
    private ClothingService clothingService;


    @Test
    public void testGetAll() {
        // Arrange
        List<Clothing> clothingList = new ArrayList<>();
        clothingList.add(new Clothing(1L, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54)));
        clothingList.add(new Clothing(2L, "Pants", "Adidas", 49, new Colour(2L, "blue"), new Places(4651, "Brugllingen", "Bleustrasse", 54)));
        when(clothingRepository.findAll()).thenReturn(clothingList);

        List<Clothing> expectedClothingList = new ArrayList<>();
        expectedClothingList.add(new Clothing(1L, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54)));
        expectedClothingList.add(new Clothing(2L, "Pants", "Adidas", 49, new Colour(2L, "blue"), new Places(4651, "Brugllingen", "Bleustrasse", 54)));


        // Act
        List<Clothing> actualClothingList = clothingService.getAll();

        // Assert
        assertEquals(expectedClothingList, actualClothingList);
        if (expectedClothingList.equals(actualClothingList)) {
            System.out.println("Clothing " + expectedClothingList + " found");
        } else {
            System.out.println("Clothing " + expectedClothingList + " not found");
        }
    }

    @Test
    public void testGetWithColor() {
        List<Clothing> clothingListToReturn = new ArrayList<>();
        clothingListToReturn.add(new Clothing(1L, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54)));
        clothingListToReturn.add(new Clothing(2L, "Pants", "Adidas", 49, new Colour(2L, "blue"), new Places(4651, "Brugllingen", "Bleustrasse", 54)));
        when(clothingRepository.findAll()).thenReturn(clothingListToReturn);

        List<Clothing> expectedList = new ArrayList<>();
        expectedList.add(new Clothing(1L, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54)));
        when(clothingRepository.findAll()).thenReturn(clothingListToReturn);

        // Act
        List<Clothing> actualClothingList = clothingService.getWithColor("Red");

        // Assert
        assertEquals(expectedList, actualClothingList);
        if (expectedList.equals(actualClothingList)) {
            System.out.println("Clothing " + expectedList + " found");
        } else {
            System.out.println("Clothing " + expectedList + " not found");
        }
    }

    @Test
    public void testGetById() {
        // Arrange
        Long id = 1L;
        Clothing clothing = new Clothing(id, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54));
        when(clothingRepository.findById(id)).thenReturn(Optional.of(clothing));

        Clothing expectedClothing = new Clothing(id, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54));

        // Act
        Clothing actualClothing = clothingService.getById(id);

        // Assert
        assertEquals(expectedClothing, actualClothing);
        if (expectedClothing.equals(actualClothing)) {
            System.out.println("Clothing " + expectedClothing + " found");
        } else {
            System.out.println("Clothing " + expectedClothing + " not found");
        }
    }

    @Test
    public void testGetByIdNotFound() { //Here
        Long id = 1L;
        when(clothingRepository.findById(id)).thenThrow(EntityNotFoundException.class);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> clothingService.getById(id));

        assertNull(exception.getMessage());
        if (exception.getMessage() == null) {
            System.out.println("Clothing " + id + " not found");
        } else {
            System.out.println("Clothing " + id + " found");
        }
    }

    @Test
    public void testDeleteClothingById() {
        // Arrange
        Long id = 1L;

        // Act
        MessageResponse messageResponse = clothingService.deleteClothingById(id);

        // Assert
        verify(clothingRepository, times(1)).deleteById(id);
        assertEquals("Clothing " + id + " gelöscht", messageResponse.getMessage());
        if (messageResponse.getMessage().equals("Clothing " + id + " gelöscht")) {
            System.out.println("Clothing " + id + " deleted");
        } else {
            System.out.println("Clothing " + id + " not deleted");
        }
    }

    @Test
    public void testInsertClothing() {
        // Arrange
        Clothing clothing = new Clothing(null, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54));
        Clothing clothingCheck = new Clothing(null, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54));
        Clothing expectedClothing = new Clothing(1L, "Shirt", "Nike", 29, new Colour(2L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54));
        when(clothingRepository.save(any(Clothing.class)))
                .thenAnswer(invocation -> {
                    Clothing inputClothing = invocation.getArgument(0);
                    return inputClothing.equals(clothing) ? expectedClothing : new Clothing();
                });

        // Act
        Clothing actualClothing = clothingService.insertClothing(clothingCheck);

        // Assert
        assertEquals(expectedClothing, actualClothing);
        if (expectedClothing.equals(actualClothing)) {
            System.out.println("Clothing " + expectedClothing + " inserted");
        } else {
            System.out.println("Clothing " + expectedClothing + " not inserted");
        }
    }

    @Test
    public void testUpdateClothingById() {
        // Arrange
        Long id = 1L;
        Clothing clothing = new Clothing(id, "Shirt", "Nike", 29, new Colour(1L, "red"), new Places(4651, "Brugllingen", "Bleustrasse", 54));
        Clothing expectedClothing = new Clothing(id, "T-Shirt", "Adidas", 39, new Colour(2L, "blue"), new Places(4651, "Brugllingen", "Bleustrasse", 54));

        when(clothingRepository.save(any(Clothing.class)))
                .thenAnswer(invocation -> {
                    Clothing inputClothing = invocation.getArgument(0);
                    return inputClothing == clothing ? clothing : new Clothing();
                });
        when(clothingRepository.findById(id)).thenReturn(Optional.of(clothing));

        // Act
        Clothing actualClothing = clothingService.updateClothingById(expectedClothing, id);

        // Assert
        assertEquals(expectedClothing, actualClothing);
        if (expectedClothing.equals(actualClothing)) {
            System.out.println("Clothing " + expectedClothing + " updated");
        } else {
            System.out.println("Clothing " + expectedClothing + " not updated");
        }
    }
}
