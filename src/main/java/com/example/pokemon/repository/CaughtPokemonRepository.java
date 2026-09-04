package com.example.pokemon.repository;

import com.example.pokemon.entity.CaughtPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaughtPokemonRepository extends JpaRepository<CaughtPokemon, Long> {
}
