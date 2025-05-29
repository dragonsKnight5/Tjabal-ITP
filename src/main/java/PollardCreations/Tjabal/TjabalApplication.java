package PollardCreations.Tjabal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"PollardCreations.Tjabal.Services"})
public class TjabalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TjabalApplication.class, args);
	}

}
