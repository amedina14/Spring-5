package com.bolsadeideas.springboot.app;

//import java.nio.file.Paths;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

//	private final Logger log = LoggerFactory.getLogger(getClass());
	/*
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		//toUri: agrega el esquema "file:" al path
		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
		
		log.info(resourcePath);
		
		registry.addResourceHandler("/uploads/**") // registra la risorsa da usare, per visualizzare l'immagine.
//		.addResourceLocations("file:/C:/Temp/uploads/");
		.addResourceLocations(resourcePath);  // indica l'ubicazione fisica di quella risorsa.
		
	}
	*/

	
}
