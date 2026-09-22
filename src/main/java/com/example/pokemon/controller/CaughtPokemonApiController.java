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

    // GET /api/caught -> 收服清單
    @GetMapping
    public List<CaughtPokemon> list() {
        return caughtPokemonService.findAll();
    }

    // GET /api/caught/{id} -> 單筆收服紀錄
    @GetMapping("/{id}")
    public CaughtPokemon get(@PathVariable Long id) {
        return caughtPokemonService.findById(id);
    }

    // POST /api/caught -> 新增，前端傳 JSON body 會自動轉成 CaughtPokemonRequest 物件
    @PostMapping
    public void create(@RequestBody CaughtPokemonRequest request) {
        caughtPokemonService.create(request.getPokemonId(), request.getNickname(), request.getCaughtDate());
    }

    // PUT /api/caught/{id} -> 更新
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CaughtPokemonRequest request) {
        caughtPokemonService.update(id, request.getPokemonId(), request.getNickname(), request.getCaughtDate());
    }

    // DELETE /api/caught/{id} -> 刪除（放生）
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        caughtPokemonService.deleteById(id);
    }
}
