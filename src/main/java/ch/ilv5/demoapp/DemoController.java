package ch.ilv5.demoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format ("Hello %s!", name);
    }

    @GetMapping("/api/hello2")
    public ResponseEntity<Demo> hello2 (@RequestParam(value = "name", defaultValue = "World") String name) {
        Demo demo = new Demo(name, 0L);
        return new ResponseEntity<>(demo, HttpStatus.OK);
    }
    @GetMapping("/api/hello2/{id}")
    public ResponseEntity<Demo> hello2 (@PathVariable long id) {
        Demo demo = new Demo( "Fynn", id);
        return new ResponseEntity<>(demo, HttpStatus.OK);
    }
}
