package ch.ilv5.demoapp;

import lombok.Data;

@Data
public class Demo {

    private Long id;
    private String name;
    private String helloString;

    public Demo(String name, Long id) {
        this.id = id;
        this.name = name;
        this.helloString = String.format("Hello %s!", name);
    }
}
