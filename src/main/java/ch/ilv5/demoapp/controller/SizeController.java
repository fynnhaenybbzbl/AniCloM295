package ch.ilv5.demoapp.controller;

import ch.ilv5.demoapp.base.MessageResponse;
import ch.ilv5.demoapp.model.Size;
import ch.ilv5.demoapp.service.SizeService;
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
    public ResponseEntity<List<Size>> all() {
        List<Size> result = sizeService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/size{id}")
    public ResponseEntity<Size> one(@PathVariable Long id) {
        Size size = sizeService.getById(id);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSize/{id}")
    public ResponseEntity<MessageResponse> deleteSize(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sizeService.deleteSize(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/postSize")
    public ResponseEntity<Size> newSize(@Valid @RequestBody Size size) {
        Size savedSize = sizeService.insertSize(size);
        return new ResponseEntity<>(savedSize, HttpStatus.OK);
    }

    @PutMapping("/updateSize/{id}")
    public ResponseEntity<Size> updateSize(@Valid @RequestBody Size size, @PathVariable Long id) {
        Size savedSize = sizeService.updateSize(size, id);
        return new ResponseEntity<>(savedSize, HttpStatus.OK);
    }
}
