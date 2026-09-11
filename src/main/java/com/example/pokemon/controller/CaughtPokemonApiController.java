package com.example.pokemon.controller;

import com.example.pokemon.entity.CaughtPokemon;
import com.example.pokemon.service.CaughtPokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 給 Angular 前端使用的收服紀錄 CRUD API，回傳 JSON。
 */
@RestController
@RequestMapping("/api/caught")
@RequiredArgsConstructor
public class CaughtPokemonApiController {

    private final CaughtPokemonService caughtPokemonService;

    @GetMapping
    public List<CaughtPokemon> list() {
        return caughtPokemonService.findAll();
    }

    @GetMapping("/{id}")
    public CaughtPokemon get(@PathVariable Long id) {
        return caughtPokemonService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody CaughtPokemonRequest request) {
        caughtPokemonService.create(request.getPokemonId(), request.getNickname(), request.getCaughtDate());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CaughtPokemonRequest request) {
        caughtPokemonService.update(id, request.getPokemonId(), request.getNickname(), request.getCaughtDate());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        caughtPokemonService.deleteById(id);
    }
}
