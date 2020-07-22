package it.myalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("it.myalert")
@SpringBootApplication
@EnableWebMvc
public class MyAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAlertApplication.class, args);
	}

}
