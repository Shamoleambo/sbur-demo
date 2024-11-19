package com.tidz.sbur_rest_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SburRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SburRestDemoApplication.class, args);
    }

    class Coffee {
        private final String id;
        private String name;

        public Coffee(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Coffee(String name) {
            this(UUID.randomUUID().toString(), name);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @RestController
    @RequestMapping("/")
    class RestApiDemoController {
        private List<Coffee> coffees = new ArrayList<>();

        public RestApiDemoController() {
            coffees.addAll(List.of(
                    new Coffee("Café Cereza"),
                    new Coffee("Café Ganador"),
                    new Coffee("Café Lareño"),
                    new Coffee("Café Três Pontas")
            ));
        }

        @GetMapping("/coffees")
        Iterable<Coffee> getCoffees() {
            return coffees;
        }

        @GetMapping("/coffees/{id}")
        Optional<Coffee> getCoffeeById(@PathVariable String id) {
            for (Coffee c : coffees) {
                if (c.getId().equals(id)) {
                    return Optional.of(c);
                }
            }

            return Optional.empty();
        }

        @PostMapping("/coffees")
        Coffee postCoffee(@RequestBody Coffee coffee) {
            coffees.add(coffee);
            return coffee;
        }

    }

}
