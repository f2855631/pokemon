package com.example.pokemon.service;

import com.example.pokemon.entity.CaughtPokemon;
import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.repository.CaughtPokemonRepository;
import com.example.pokemon.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaughtPokemonService {

    private final CaughtPokemonRepository caughtPokemonRepository;
    private final PokemonRepository pokemonRepository;

    public List<CaughtPokemon> findAll() {
        return caughtPokemonRepository.findAll();
    }

    public CaughtPokemon findById(Long id) {
        return caughtPokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到收服紀錄 id=" + id));
    }

    public void create(Long pokemonId, String nickname, LocalDate caughtDate) {
        CaughtPokemon caught = new CaughtPokemon();
        applyChanges(caught, pokemonId, nickname, caughtDate);
        caughtPokemonRepository.save(caught);
    }

    public void update(Long id, Long pokemonId, String nickname, LocalDate caughtDate) {
        CaughtPokemon caught = findById(id);
        applyChanges(caught, pokemonId, nickname, caughtDate);
        caughtPokemonRepository.save(caught);
    }

    public void deleteById(Long id) {
        caughtPokemonRepository.deleteById(id);
    }

    private void applyChanges(CaughtPokemon caught, Long pokemonId, String nickname, LocalDate caughtDate) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new IllegalArgumentException("找不到寶可夢 id=" + pokemonId));
        caught.setPokemon(pokemon);
        caught.setNickname(nickname);
        caught.setCaughtDate(caughtDate != null ? caughtDate : LocalDate.now());
    }
}
