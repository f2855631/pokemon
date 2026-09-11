package com.example.pokemon.controller;

import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 給 Angular 前端使用的寶可夢圖鑑 API，回傳 JSON。
 */
@RestController
@RequestMapping("/api/pokemons")
@RequiredArgsConstructor
public class PokemonApiController {

    private final PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> list() {
        return pokemonService.findAll();
    }

    @GetMapping("/{id}")
    public Pokemon get(@PathVariable Long id) {
        return pokemonService.findById(id);
    }
}
