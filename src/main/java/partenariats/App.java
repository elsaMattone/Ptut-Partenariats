package partenariats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	// Configure le mapper pour qu'il respecte les annotations JAXB, en particulier
	// XMLTransient
	public JaxbAnnotationModule JaxbAnnotationModule() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		return module;
	}
}
