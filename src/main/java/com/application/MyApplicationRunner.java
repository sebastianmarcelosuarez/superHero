package com.application;

import com.application.service.SuperHeroService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

	private final SuperHeroService superHeroService;

	public MyApplicationRunner(SuperHeroService superHeroService) {
		this.superHeroService = superHeroService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {


	}
}
