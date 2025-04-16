package com.softmarket.ApiSoftMarket;

import com.softmarket.ApiSoftMarket.entity.Authentication;
import com.softmarket.ApiSoftMarket.repository.AuthenticationRepository;
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
			auth.setClient_id("9de1ceb1-0a11-44c0-b8f9-e2f743cb7427");
			auth.setClient_secret("lf8JRkDhDSnPVoadfo5FIZmKkPNpNw51AhNTFbxD");
			auth.setGran_type("password");
			auth.setUsername("sandbox@factus.com.co");
			auth.setPassword("sandbox2024%");

			authenticationRepository.save(auth);
		} else {
			System.out.println("ℹ️ Usuario 'admin' ya existe en Authentication.");
		}
	}
}
