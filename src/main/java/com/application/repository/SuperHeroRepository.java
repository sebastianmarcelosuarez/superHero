package com.application.repository;

import com.application.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {

	@Query("SELECT s FROM SuperHero s")
	Optional<List<SuperHero>> findAllSuperHeroes();

	Optional<List<SuperHero>>findBySuperHeroNameContainingIgnoreCase(String superHeroName);

}
