package ch.ilv5.aniclo;

import ch.ilv5.aniclo.model.Colour;
import ch.ilv5.aniclo.repository.ColourRepository;
import ch.ilv5.aniclo.service.ColourService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ColourServiceTest {

    @Autowired
    private ColourService colourService;

    @MockBean
    private ColourRepository colourRepository;

    @Test
    public void testGetById() {
        when(colourRepository.findById(1L)).thenReturn(Optional.of(new Colour()));
        assertNotNull(colourService.getById(1L));

        when(colourRepository.findById(Long.MAX_VALUE)).thenReturn(Optional.of(new Colour()));
        assertNotNull(colourService.getById(Long.MAX_VALUE));

        when(colourRepository.findById(0L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> colourService.getById(0L));

        when(colourRepository.findById(Long.MAX_VALUE + 1)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> colourService.getById(Long.MAX_VALUE + 1));
    }

}
