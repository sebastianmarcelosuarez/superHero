package com.application.service;

import com.application.model.SuperHero;
import com.application.model.dto.SuperHeroDto;
import com.application.repository.SuperHeroRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SuperHeroServiceTest {

	@Resource
	private SuperHeroRepository superHeroRepository;

	@Test
	@DisplayName("Test if superHero is created in DB")
	public void createSuperHero() {
		SuperHeroService superHeroService = new SuperHeroService(superHeroRepository);

		SuperHeroDto dto = new SuperHeroDto("Morales","Spiderman", "icecream");
		Optional<SuperHero> superHeroOption = superHeroService.saveSuperHero(dto);

		assertTrue( superHeroOption.isPresent());

		SuperHero superHero = superHeroOption.get();

		assertNotNull( superHero.getId());
		assertEquals("Morales", superHero.getName());
		assertEquals("Spiderman", superHero.getSuperHeroName());
		assertEquals("icecream", superHero.getFavoriteFood());
	}

}
