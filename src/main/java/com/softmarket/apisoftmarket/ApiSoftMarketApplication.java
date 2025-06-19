package com.softmarket.apisoftmarket;

import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.repository.AuthenticationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiSoftMarketApplication implements CommandLineRunner {
	@Autowired
	private AuthenticationRepository authenticationRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(ApiSoftMarketApplication.class, args);

	}

	@Override
	@Transactional
	public void run(String... args) {

		/*try {
      entityManager.createNativeQuery("DROP TABLE authentication CASCADE").executeUpdate();
      entityManager.createNativeQuery("DROP TABLE authorization_token CASCADE").executeUpdate();
      entityManager.createNativeQuery("DROP TABLE customer_api CASCADE").executeUpdate();
      System.out.println("🗑️ Tablas eliminadas correctamente");
    } catch (Exception e) {
      System.out.println("⚠️ Error al eliminar las tablas: " + e.getMessage());
    }*/
		if (authenticationRepository.findByUsername("sandbox@factus.com.co").isEmpty()) {
			Authentication auth = new Authentication();
			auth.setClient_id("9ed98af2-5a02-47f2-b2be-992e34b4c5d7");
			auth.setClient_secret("vHntJnJw4nBrx1HkztDbdh4VtBEPrBuBbYbknizg");
			auth.setGran_type("password");
			auth.setUsername("sandbox@factus.com.co");
			auth.setPassword("sandbox2024%");

			authenticationRepository.save(auth);
		} else {
			System.out.println("ℹ️ Usuario 'admin' ya existe en Authentication.");
		}
	}
}
