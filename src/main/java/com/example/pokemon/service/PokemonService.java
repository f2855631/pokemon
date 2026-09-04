package com.example.pokemon.service;

import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public Pokemon findById(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到寶可夢 id=" + id));
    }
}
