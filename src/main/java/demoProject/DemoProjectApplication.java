package demoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "demoProject")
@EntityScan("demoProject")
public class DemoProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}
}
