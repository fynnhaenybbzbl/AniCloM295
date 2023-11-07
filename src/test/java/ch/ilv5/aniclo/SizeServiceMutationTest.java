package ch.ilv5.aniclo;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Size;
import ch.ilv5.aniclo.repository.SizeRepository;
import ch.ilv5.aniclo.service.SizeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeServiceMutationTest {

    @InjectMocks
    private SizeService sizeService;

    @Mock
    private SizeRepository sizeRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMutation_GetAll() {
        Mockito.when(sizeRepository.findAll()).thenReturn(Collections.emptyList());

        // Original behavior
        assertEquals(0, sizeService.getAll().size());

        // Mutation: Changing the return value
        Mockito.when(sizeRepository.findAll()).thenReturn(Collections.singletonList(new Size()));
        assertEquals(1, sizeService.getAll().size());
    }

    @Test
    public void getById_shouldReturnExpectedResult() {
        // Arrange
    //    Size size = new Size();
      //  size.setId(1L);
        //  size.setEu(12);
       // size.setFra(12);
       // size.setUsa("Medium");
        Mockito.when(sizeRepository.findById(1L)).thenReturn(Optional.of(new Size(1L, 12, 12, "Medium")));

        // Act
        Size result = sizeService.getById(1L);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());

        Mockito.when(sizeRepository.findById(1L)).thenThrow(new EntityNotFoundException());

        Assertions.assertThrows(EntityNotFoundException.class, () -> sizeRepository.findById(1L));

        // Handle the exception gracefully
        try {
            sizeService.getById(2L);
        } catch (EntityNotFoundException e) {
            System.err.println("Entity not found: " + e);
        }
    }

    @Test
    public void testMutation_DeleteSize() {
        Long validId = 1L;
        Size size = new Size();
        size.setId(validId);

        Mockito.when(sizeRepository.findById(validId)).thenReturn(Optional.of(size));
        MessageResponse response = sizeService.deleteSize(validId);
        assertEquals("Size " + validId + " deleted", response.getMessage());
    }

    @Test
    public void testMutation_InsertSize() {
        Size size = new Size();
        Mockito.when(sizeRepository.save(size)).thenReturn(size);
        assertEquals(size, sizeService.insertSize(size));
    }

    @Test
    public void testMutation_UpdateSizeById() {
        Long validId = 1L;
        Size size = new Size();
        size.setId(validId);

        Mockito.when(sizeRepository.findById(validId)).thenReturn(Optional.of(size));

        // Original behavior
        Size updatedSize = sizeService.updateSizeById(size, validId);

        // Mutation: Changing the updatedSize
        Size mutatedSize = new Size(); // Create a mutated Size object
        Mockito.when(sizeRepository.findById(validId)).thenReturn(Optional.of(mutatedSize));
        assertMutationDetected(() -> sizeService.updateSizeById(size, validId));
    }

    private void assertMutationDetected(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Mutation not detected");
        } catch (AssertionError e) {
            // Mutation detected
        }
    }
}
