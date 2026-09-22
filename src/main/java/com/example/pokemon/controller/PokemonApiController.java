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
@RestController // 跟 @Controller 不同，方法回傳值會直接轉成 JSON，不會去找 Thymeleaf 模板
@RequestMapping("/api/pokemons") // 這個 class 底下的路由都以 /api/pokemons 開頭
@RequiredArgsConstructor
public class PokemonApiController {

    private final PokemonService pokemonService;

    // GET /api/pokemons -> 回傳整份圖鑑的 JSON 陣列
    @GetMapping
    public List<Pokemon> list() {
        return pokemonService.findAll();
    }

    // GET /api/pokemons/{id} -> 回傳單一寶可夢的 JSON
    @GetMapping("/{id}")
    public Pokemon get(@PathVariable Long id) {
        return pokemonService.findById(id);
    }
}
