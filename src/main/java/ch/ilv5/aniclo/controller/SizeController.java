package ch.ilv5.aniclo.controller;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Size;
import ch.ilv5.aniclo.service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/size")
    public ResponseEntity<List<Size>> getAll() {
        List<Size> result = sizeService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/size{id}")
    public ResponseEntity<Size> getById(@PathVariable Long id) {
        Size size = sizeService.getById(id);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSize/{id}")
    public ResponseEntity<MessageResponse> deleteSizeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sizeService.deleteSize(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/postSize")
    public ResponseEntity<Size> addNewSize(@Valid @RequestBody Size size) {
        Size savedSize = sizeService.insertSize(size);
        return new ResponseEntity<>(savedSize, HttpStatus.OK);
    }

    @PutMapping("/updateSize/{id}")
    public ResponseEntity<Size> updateSizeById(@Valid @RequestBody Size size, @PathVariable Long id) {
        Size savedSize = sizeService.updateSizeById(size, id);
        return new ResponseEntity<>(savedSize, HttpStatus.OK);
    }
}
