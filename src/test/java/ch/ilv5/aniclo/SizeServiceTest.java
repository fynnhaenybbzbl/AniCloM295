package ch.ilv5.aniclo;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Size;
import ch.ilv5.aniclo.repository.SizeRepository;
import ch.ilv5.aniclo.service.SizeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SizeServiceTest {
    private SizeService sizeService;

    @Mock
    private SizeRepository sizeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sizeService = new SizeService(sizeRepository);
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<Size> sizes = Arrays.asList(
                new Size(1L, 36, 38, "US 6"),
                new Size(2L, 38, 40, "US 8")
        );
        when(sizeRepository.findAll()).thenReturn(sizes);

        // Act
        List<Size> result = sizeService.getAll();

        // Assert
        assertEquals(sizes, result);
        if (sizes == result) {
            System.out.println("Size " + sizes + " found");
        } else {
            System.out.println("Size " + sizes + " not found");
        }
        verify(sizeRepository, times(1)).findAll();
    }

    @Test
    public void testGetById() {
        // Arrange
        Size size = new Size(1L, 36, 38, "US 6");
        when(sizeRepository.findById(1L)).thenReturn(Optional.of(size));

        // Act
        Size result = sizeService.getById(1L);

        // Assert
        assertEquals(size, result);
        if (size == result) {
            System.out.println("Size " + size + " found");
        } else {
            System.out.println("Size " + size + " not found");
        }
        verify(sizeRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteSize() {
        // Arrange
        Long id = 1L;

        // Act
        MessageResponse result = sizeService.deleteSize(id);

        // Assert
        assertEquals("Size " + id + " deleted", result.getMessage());
        if (result.getMessage().equals("Size " + id + " deleted")) {
            System.out.println("Size " + id + " deleted");
        } else {
            System.out.println("Size " + id + " not deleted");
        }
        verify(sizeRepository, times(1)).deleteById(id);
    }

    @Test
    public void testInsertSize() {
        // Arrange
        Size xsize = new Size(1L, 36, 38, "US 6");
        Size size = new Size(1L, 36, 38, "US 6");
        when(sizeRepository.save(any(Size.class)))
                .thenAnswer(invocation -> {
                    Size inputClothing = invocation.getArgument(0);
                    return inputClothing.equals(xsize) ? size : new Size();
                });
        // Act
        Size result = sizeService.insertSize(size);

        // Assert
        assertEquals(size, result);
        if ((size.equals(result))) {
            System.out.println("Size " + size + " inserted");
        } else {
            System.out.println("Size " + size + " not inserted");
        }
        verify(sizeRepository, times(1)).save(size);

    }

    @Test
    public void testUpdateSizeById() {
        // Arrange
        Long id = 1L;
        Size size = new Size(id, 36, 38, "US 6");
        Size updatedSize = new Size(id, 38, 40, "US 8");
        Size xupdatedSize = new Size(id, 38, 40, "US 8");
        when(sizeRepository.findById(id)).thenReturn(Optional.of(size));
        when(sizeRepository.save(size)).thenReturn(xupdatedSize);

        // Act
        Size result = sizeService.updateSizeById(updatedSize, id);

        // Assert
        assertEquals(updatedSize, result);
        if (updatedSize.equals(result)) {
            System.out.println("Size " + updatedSize + " updated");
        } else {
            System.out.println("Size " + updatedSize + " not updated");
        }
        verify(sizeRepository, times(1)).findById(id);
        verify(sizeRepository, times(1)).save(size);
    }
}