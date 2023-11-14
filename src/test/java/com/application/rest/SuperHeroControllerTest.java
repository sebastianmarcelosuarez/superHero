package com.application.rest;

import com.application.model.SuperHero;
import com.application.model.dto.SuperHeroDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SuperHeroControllerTest extends AbstractControllerTest {

	@Test
	@DisplayName("Test if superHero is created in DB from webcall")
	public void shouldReturnFoundPrice() throws Exception {
		// given
		SuperHeroDto superHeroDto = new SuperHeroDto("John","Rambo","Onion");
		SuperHero superHero = new SuperHero("John","Rambo","Onion");
		superHero.setId(1l);

		// when
		when(superHeroService.saveSuperHero(superHeroDto)).thenReturn(Optional.of(superHero));

		// then
		mockMvc.perform( MockMvcRequestBuilders
			.post("/superHero/")
			.content(asJsonString(superHeroDto))
			.contentType(APPLICATION_JSON)
			.accept(APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().string("{\"id\":1,\"name\":\"John\",\"superHeroName\":\"Rambo\",\"favoriteFood\":\"Onion\"}"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
