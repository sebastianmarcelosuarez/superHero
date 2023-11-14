package com.application.rest;

import com.application.aop.MyTimeLogger;
import com.application.model.SuperHero;
import com.application.model.dto.SuperHeroDto;
import com.application.service.SuperHeroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/superHero")
public class SuperHeroController {

	private final SuperHeroService superHeroService;

	public SuperHeroController(SuperHeroService superHeroService) {
		this.superHeroService = superHeroService;
	}

	@GetMapping(value = "/")
	@ResponseStatus(HttpStatus.OK)
	@MyTimeLogger
	public ResponseEntity getSuperHeroes() {
		Optional<List<SuperHero>> allSuperHeroesOptional = superHeroService.getAllSuperHeroes();
		if (allSuperHeroesOptional.isEmpty() ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(allSuperHeroesOptional);
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	@MyTimeLogger
	public ResponseEntity getSuperHeroById(@PathVariable(value = "id") Long superHeroId) {
		Optional<SuperHero> superHero = superHeroService.getSuperHeroById(superHeroId);

		if (superHero.isEmpty() ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(superHero);
	}

	@GetMapping(value = "/data")
	@ResponseStatus(HttpStatus.OK)
	@MyTimeLogger
	public ResponseEntity getSuperHeroesByName(@RequestParam String name) {
		Optional<List<SuperHero>> superHero = superHeroService.getSuperHeroBySuperName(name);

		if (superHero.isEmpty()  ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(superHero);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@MyTimeLogger
	public ResponseEntity createSuperHero(@RequestBody SuperHeroDto superHeroDto) {
		Optional<SuperHero> superHero = superHeroService.saveSuperHero(superHeroDto);

		if (superHero.isEmpty()  ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.CREATED).body(superHero);
	}

	@PutMapping("/")
	@ResponseStatus(HttpStatus.OK)
	@MyTimeLogger
	public ResponseEntity updateSuperHero(@RequestBody SuperHero superHero) {
		Optional<SuperHero> superHeroUpdated = superHeroService.updateSuperHero(superHero);

		if (superHeroUpdated.isEmpty()  ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(superHero);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	@MyTimeLogger
	public ResponseEntity deleteSuperHeroById(@PathVariable(value = "id") Long superHeroId) {
		superHeroService.deleteSuperHero(superHeroId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
