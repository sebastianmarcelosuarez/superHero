package com.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class SuperHero {


	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String superHeroName;
	private String favoriteFood;


	public SuperHero(String name, String superHeroName, String favoriteFood) {
		this.name = name;
		this.superHeroName = superHeroName;
		this.favoriteFood = favoriteFood;
	}

	public SuperHero() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperHeroName() {
		return superHeroName;
	}

	public void setSuperHeroName(String superHeroName) {
		this.superHeroName = superHeroName;
	}

	public String getFavoriteFood() {
		return favoriteFood;
	}

	public void setFavoriteFood(String favoriteFood) {
		this.favoriteFood = favoriteFood;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SuperHero that = (SuperHero) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(superHeroName, that.superHeroName) && favoriteFood.equals(that.favoriteFood);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, superHeroName, favoriteFood);
	}
}
