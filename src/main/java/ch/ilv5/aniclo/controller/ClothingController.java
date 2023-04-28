package ch.ilv5.aniclo.controller;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Clothing;
import ch.ilv5.aniclo.security.Roles;
import ch.ilv5.aniclo.service.ClothingService;
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
public class ClothingController {
    @Autowired
    private ClothingService clothingService;
    @Operation(summary = "Get all Clothing")
    @GetMapping("/clothing")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Clothing>> getAll() {
        List<Clothing> result = clothingService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Get Clothing by Id")
    @GetMapping("/clothing/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Clothing> getById(@PathVariable Long id) {
        Clothing clothing = clothingService.getById(id);
        return new ResponseEntity<>(clothing, HttpStatus.OK);
    }

    @Operation(summary = "Delete Clothing by ID")
    @DeleteMapping("/deleteClothing/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteClothingById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clothingService.deleteClothingById(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create Clothing")
    @PostMapping("/postClothing")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Clothing> addNewClothing(@Valid @RequestBody Clothing clothing) {
        Clothing savedClothing = clothingService.insertClothing(clothing);
        return new ResponseEntity<>(savedClothing, HttpStatus.OK);
    }

    @Operation(summary = "Update a Clothing Object")
    @PutMapping("/updateClothing/{id}")
    @RolesAllowed({Roles.Admin, Roles.Update})
    public ResponseEntity<Clothing> updateClothingById(@Valid @RequestBody Clothing clothing, @PathVariable Long id) {
        Clothing savedClothing = clothingService.updateClothingById(clothing, id);
        return new ResponseEntity<>(savedClothing, HttpStatus.OK);
    }
}
