package com.application.rest;

import com.application.model.SuperHero;
import com.application.model.dto.SuperHeroDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SuperHeroControllerTest extends AbstractControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	@DisplayName("Test if superHero is created in DB from webcall")
	public void createSuperheroAPICall() throws Exception {
		// given
		SuperHeroDto superHeroDto = new SuperHeroDto("John","Rambo","Onion");
		SuperHero superHero = new SuperHero("John","Rambo","Onion");
		superHero.setId(1l);

		// when
		when(superHeroService.saveSuperHero(superHeroDto)).thenReturn(Optional.of(superHero));

		// then
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders
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
