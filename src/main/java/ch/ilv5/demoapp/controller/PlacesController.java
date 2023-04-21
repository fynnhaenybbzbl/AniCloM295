package ch.ilv5.demoapp.controller;

import ch.ilv5.demoapp.base.MessageResponse;
import ch.ilv5.demoapp.model.Places;
import ch.ilv5.demoapp.model.Size;
import ch.ilv5.demoapp.service.PlacesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlacesController {
    @Autowired
    private PlacesService placesService;

    @GetMapping("/places")
    public ResponseEntity<List<Places>> all() {
        List<Places> result = placesService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/places{id}")
    public ResponseEntity<Places> one(@PathVariable Long id) {
        Places place = placesService.getById(id);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @DeleteMapping("/deletePlace/{id}")
    public ResponseEntity<MessageResponse> deletePlace(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(placesService.deletePlace(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/postPlace")
    public ResponseEntity<Places> newSize(@Valid @RequestBody Places place) {
        Places savedPlace = placesService.insertPlace(place);
        return new ResponseEntity<>(savedPlace, HttpStatus.OK);
    }

    @PutMapping("/updatePlace/{id}")
    public ResponseEntity<Places> updatePlace(@Valid @RequestBody Places place, @PathVariable Long id) {
        Places savedPlace = placesService.updatePlace(place, id);
        return new ResponseEntity<>(savedPlace, HttpStatus.OK);
    }
}
