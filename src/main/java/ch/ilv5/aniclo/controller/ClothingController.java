package ch.ilv5.aniclo.controller;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Clothing;
import ch.ilv5.aniclo.service.ClothingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClothingController {
    @Autowired
    private ClothingService clothingService;

    @GetMapping("/clothing")
    public ResponseEntity<List<Clothing>> getAll() {
        List<Clothing> result = clothingService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/clothing{id}")
    public ResponseEntity<Clothing> getById(@PathVariable Long id) {
        Clothing clothing = clothingService.getById(id);
        return new ResponseEntity<>(clothing, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClothing/{id}")
    public ResponseEntity<MessageResponse> deleteClothingById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clothingService.deleteClothingById(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/postClothing")
    public ResponseEntity<Clothing> addNewClothing(@Valid @RequestBody Clothing clothing) {
        Clothing savedClothing = clothingService.insertClothing(clothing);
        return new ResponseEntity<>(savedClothing, HttpStatus.OK);
    }

    @PutMapping("/updateClothing/{id}")
    public ResponseEntity<Clothing> updateClothingById(@Valid @RequestBody Clothing clothing, @PathVariable Long id) {
        Clothing savedClothing = clothingService.updateClothingById(clothing, id);
        return new ResponseEntity<>(savedClothing, HttpStatus.OK);
    }
}