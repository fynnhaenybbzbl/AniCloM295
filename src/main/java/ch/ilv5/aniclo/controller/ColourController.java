package ch.ilv5.aniclo.controller;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Colour;
import ch.ilv5.aniclo.service.ColourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ColourController {
    @Autowired
    private ColourService colourService;

    @Operation(summary = "Get all Colours")
    @GetMapping("/colour")
    public ResponseEntity<List<Colour>> getAll() {
        List<Colour> result = colourService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Get Colour by ID")
    @GetMapping("/colour{id}")
    public ResponseEntity<Colour> getById(@PathVariable Long id) {
        Colour colour = colourService.getById(id);
        return new ResponseEntity<>(colour, HttpStatus.OK);
    }

    @Operation(summary = "Delete Colour by ID")
    @DeleteMapping("/deleteColour/{id}")
    public ResponseEntity<MessageResponse> deleteColourById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(colourService.deleteColourById(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create Colour")
    @PostMapping("/postColour")
    public ResponseEntity<Colour> addNewColour(@Valid @RequestBody Colour colour) {
        Colour savedColour = colourService.insertColour(colour);
        return new ResponseEntity<>(savedColour, HttpStatus.OK);
    }

    @Operation(summary = "Update a Colour Object")
    @PutMapping("/updateColour/{id}")
    public ResponseEntity<Colour> updateColourById(@Valid @RequestBody Colour colour, @PathVariable Long id) {
        Colour savedColour = colourService.updatecolourById(colour, id);
        return new ResponseEntity<>(savedColour, HttpStatus.OK);
    }
}
