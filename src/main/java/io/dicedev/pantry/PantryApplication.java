package io.dicedev.pantry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PantryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PantryApplication.class, args);
	}

}
