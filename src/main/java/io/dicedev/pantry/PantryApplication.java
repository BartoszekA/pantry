package io.dicedev.pantry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PantryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PantryApplication.class, args);
    }

}
