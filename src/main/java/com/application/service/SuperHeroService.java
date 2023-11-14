package com.application.service;

import com.application.model.SuperHero;
import com.application.model.dto.SuperHeroDto;
import com.application.repository.SuperHeroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SuperHeroService {

	private final SuperHeroRepository superHeroRepository;

	public SuperHeroService(SuperHeroRepository superHeroRepository) {
		this.superHeroRepository = superHeroRepository;
	}


	public Optional<List<SuperHero>> getAllSuperHeroes() {
		Optional<List<SuperHero>> allSuperHeroes = superHeroRepository.findAllSuperHeroes();

		if (allSuperHeroes.isEmpty() || allSuperHeroes.get().isEmpty() ) return Optional.empty();

		return allSuperHeroes;
	}

	public Optional<SuperHero> getSuperHeroById(Long superHeroId) {
		Optional<SuperHero> superHero = superHeroRepository.findById(superHeroId);

		if (superHero.isEmpty()) return Optional.empty();

		return superHero;
	}

	public Optional<List<SuperHero>> getSuperHeroBySuperName(String superHeroName) {
		Optional<List<SuperHero>> superHeroes = superHeroRepository.findBySuperHeroNameContainingIgnoreCase(superHeroName);

		if (superHeroes.isEmpty() || superHeroes.get().isEmpty() ) return Optional.empty();

		return superHeroes;
	}

	public Optional<SuperHero> saveSuperHero(SuperHeroDto superHeroDto) {

		SuperHero superHero = superHeroRepository.save(new SuperHero(superHeroDto.name(), superHeroDto.superHeroName(), superHeroDto.favoriteFood()) );

		if (Objects.isNull(superHero) ) return Optional.empty();

		return Optional.of(superHero);
	}

	public Optional<SuperHero> updateSuperHero(SuperHero superHero) {

		SuperHero superHeroUpdated = superHeroRepository.save(superHero);

		if (Objects.isNull(superHero) ) return Optional.empty();

		return Optional.of(superHeroUpdated);
	}

	public void deleteSuperHero(Long id) {
		superHeroRepository.deleteById(id);

	}

	public void initialize() {

		List superHeroList = new ArrayList<SuperHero>();

		SuperHero superHero1 = new SuperHero();
		superHero1.setName("Bruce Wayne");
		superHero1.setSuperHeroName("Batman");
		superHero1.setFavoriteFood("Hamburguesas");

		SuperHero superHero2 = new SuperHero();
		superHero2.setName("Clark Kent");
		superHero2.setSuperHeroName("Superman");
		superHero2.setFavoriteFood("Lasagna");

		SuperHero superHero3 = new SuperHero();
		superHero3.setName("Bruce Wayne2");
		superHero3.setSuperHeroName("Batman2");
		superHero3.setFavoriteFood("Atun");

		superHeroList.add(superHero1);
		superHeroList.add(superHero2);
		superHeroList.add(superHero3);

		superHeroRepository.saveAll(superHeroList);

	}


}

