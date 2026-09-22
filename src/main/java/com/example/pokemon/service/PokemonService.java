package com.example.pokemon.service;

import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 圖鑑相關的業務邏輯層，Controller 不會直接碰 Repository，都要先經過這裡
@Service
@RequiredArgsConstructor // 自動產生「只包含 final 欄位」的建構子，Spring 會用它自動注入 PokemonRepository
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    // 取得整份圖鑑
    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    // 依 id 找單一寶可夢，找不到就丟例外（讓呼叫的地方知道發生錯誤）
    public Pokemon findById(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到寶可夢 id=" + id));
    }
}
