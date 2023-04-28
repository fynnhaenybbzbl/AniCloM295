package ch.ilv5.aniclo.controller;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Places;
import ch.ilv5.aniclo.security.Roles;
import ch.ilv5.aniclo.service.PlacesService;
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
@SecurityRequirement(name = "bearerAuth")
public class PlacesController {
    @Autowired
    private PlacesService placesService;

    @Operation(summary = "Get all Places")
    @GetMapping("/places")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Places>> getAll() {
        List<Places> result = placesService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Get Places by ID")
    @GetMapping("/places/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Places> getById(@PathVariable Long id) {
        Places place = placesService.getById(id);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @Operation(summary = "Delete Place by ID")
    @DeleteMapping("/deletePlace/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deletePlaceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(placesService.deletePlaceById(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create Place")
    @PostMapping("/postPlace")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Places> addNewPlace(@Valid @RequestBody Places place) {
        Places savedPlace = placesService.insertPlace(place);
        return new ResponseEntity<>(savedPlace, HttpStatus.OK);
    }

    @Operation(summary = "Update a Place Object")
    @PutMapping("/updatePlace/{id}")
    @RolesAllowed({Roles.Admin, Roles.Update})
    public ResponseEntity<Places> updatePlaceById(@Valid @RequestBody Places place, @PathVariable Long id) {
        Places savedPlace = placesService.updatePlaceById(place, id);
        return new ResponseEntity<>(savedPlace, HttpStatus.OK);
    }
}
