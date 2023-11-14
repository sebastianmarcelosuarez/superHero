package com.application.repository;

import com.application.model.SuperHero;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {

	@Query("SELECT s FROM SuperHero s")
	@Cacheable("allSuperHeroes")
	Optional<List<SuperHero>> findAllSuperHeroes();

	@Cacheable("AllSuperHeroesByName")
	Optional<List<SuperHero>>findBySuperHeroNameContainingIgnoreCase(String superHeroName);

}
