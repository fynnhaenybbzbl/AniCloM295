package ch.ilv5.aniclo.controller;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Size;
import ch.ilv5.aniclo.security.Roles;
import ch.ilv5.aniclo.service.SizeService;
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
@SecurityRequirement(name = "bearerAuth") //Todo: add me
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @Operation(summary = "Get all Size")
    @GetMapping("/size")
    @RolesAllowed(Roles.Read)

    public ResponseEntity<List<Size>> getAll() {
        List<Size> result = sizeService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Get Size by ID")
    @GetMapping("/size/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Size> getById(@PathVariable Long id) {
        Size size = sizeService.getById(id);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    @Operation(summary = "Delete Size by ID")
    @DeleteMapping("/deleteSize/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteSizeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sizeService.deleteSize(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Create Size")
    @PostMapping("/postSize")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Size> addNewSize(@Valid @RequestBody Size size) {
        Size savedSize = sizeService.insertSize(size);
        return new ResponseEntity<>(savedSize, HttpStatus.OK);
    }

    @Operation(summary = "Update a Size Object")
    @PutMapping("/updateSize/{id}")
    @RolesAllowed({Roles.Admin, Roles.Update})
    public ResponseEntity<Size> updateSizeById(@Valid @RequestBody Size size, @PathVariable Long id) {
        Size savedSize = sizeService.updateSizeById(size, id);
        return new ResponseEntity<>(savedSize, HttpStatus.OK);
    }
}
