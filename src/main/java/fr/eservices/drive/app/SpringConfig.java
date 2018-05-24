package fr.eservices.drive.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// use this class as a configuration class for spring context
@Configuration
@ComponentScan(basePackages={"fr.eservices.drive.app","fr.eservices.drive.dao.impl","fr.eservices.drive.model","fr.eservices.drive.util"})
public class SpringConfig {

	// expose this as a bean for spring context
	// expose an entity manager for DAO using JPA
	
	@Bean
	EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("myApp");
		
	}
	
	@Bean
	EntityManager entityManager(EntityManagerFactory emf) {
		return emf.createEntityManager();
	}
	

}
