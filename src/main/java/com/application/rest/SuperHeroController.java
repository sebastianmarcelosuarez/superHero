package com.application.rest;

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
	public ResponseEntity getSuperHeroes() {
		Optional<List<SuperHero>> priceOptional = superHeroService.getAllSuperHeroes();


		//Consume external API:
			//final String uri = "http://localhost:8080/springrestexample/employees.xml";
		//    final String uri = "https://catfact.ninja/fact";

		//	RestTemplate restTemplate = new RestTemplate();
		//	String result = restTemplate.getForObject(uri, String.class);

		//	System.out.println(result);

			//Consume external API: END

		if (priceOptional.isEmpty() ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(priceOptional);
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity getSuperHeroById(@PathVariable(value = "id") Long superHeroId) {
		Optional<SuperHero> superHero = superHeroService.getSuperHeroById(superHeroId);

		if (superHero.isEmpty() ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(superHero);
	}

	@GetMapping(value = "/data")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity getSuperHeroesByName(@RequestParam String name) {
		Optional<List<SuperHero>> superHero = superHeroService.getSuperHeroBySuperName(name);

		if (superHero.isEmpty()  ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(superHero);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity createSuperHero(@RequestBody SuperHeroDto superHeroDto) {
		Optional<SuperHero> superHero = superHeroService.saveSuperHero(superHeroDto);

		if (superHero.isEmpty()  ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.CREATED).body(superHero);
	}

	@PutMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity updateSuperHero(@RequestBody SuperHero superHero) {
		Optional<SuperHero> superHeroUpdated = superHeroService.updateSuperHero(superHero);

		if (superHeroUpdated.isEmpty()  ) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(superHero);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity deleteSuperHeroById(@PathVariable(value = "id") Long superHeroId) {
		superHeroService.deleteSuperHero(superHeroId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
