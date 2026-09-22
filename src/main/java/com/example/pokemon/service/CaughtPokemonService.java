package com.example.pokemon.service;

import com.example.pokemon.entity.CaughtPokemon;
import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.repository.CaughtPokemonRepository;
import com.example.pokemon.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// @Service：把這個 class 變成物件，交給 Spring 管理（放進 IoC 容器）
// @RequiredArgsConstructor：底下兩個 final 欄位會變成建構子的必要參數，
// Spring 建立這個物件時，自動把已經管理好的 Repository 塞進來（建構子注入）
@Service
@RequiredArgsConstructor
public class CaughtPokemonService {

    private final CaughtPokemonRepository caughtPokemonRepository;
    private final PokemonRepository pokemonRepository; // 新增/更新時要靠它去查、驗證 pokemonId 是否存在

    // 撈全部收服紀錄：直接轉呼叫 Repository 繼承來的內建方法
    public List<CaughtPokemon> findAll() {
        return caughtPokemonRepository.findAll();
    }

    // 依 id 查單筆：findById 回傳「可能有可能沒有」的 Optional，
    // orElseThrow 負責拆箱——有就回傳，沒有就丟例外中斷（不回傳 null）
    public CaughtPokemon findById(Long id) {
        return caughtPokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到收服紀錄 id=" + id));
    }

    // 新增：先 new 一個空物件，呼叫 applyChanges 把資料套進去，再存進資料庫
    public void create(Long pokemonId, String nickname, LocalDate caughtDate) {
        CaughtPokemon caught = new CaughtPokemon();
        applyChanges(caught, pokemonId, nickname, caughtDate);
        caughtPokemonRepository.save(caught);
    }

    // 更新：先查出既有物件（不是 new 全新的），套用新資料後再存回去（等於覆蓋）
    public void update(Long id, Long pokemonId, String nickname, LocalDate caughtDate) {
        CaughtPokemon caught = findById(id);
        applyChanges(caught, pokemonId, nickname, caughtDate);
        caughtPokemonRepository.save(caught);
    }

    public void deleteById(Long id) {
        caughtPokemonRepository.deleteById(id);
    }

    // create、update 共用的「套用變更」邏輯，設 private 是因為：
    // 這裡只負責把資料塞進物件，不負責 save()，若讓外部直接呼叫，
    // 容易忘記接著存檔，資料只填好但沒存進資料庫（做半套）
    private void applyChanges(CaughtPokemon caught, Long pokemonId, String nickname, LocalDate caughtDate) {
        // 三個欄位，三種不同的「沒有值」處理方式：
        // pokemon：查不到就直接拋例外中斷（沒有備用值可用）
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new IllegalArgumentException("找不到寶可夢 id=" + pokemonId));
        caught.setPokemon(pokemon);
        // nickname：沒有特殊處理，null 就直接存 null
        caught.setNickname(nickname);
        // caughtDate：唯一有預設值邏輯的欄位——三元運算子，
        // 「條件 ? 條件成立的值 : 條件不成立的值」：有給日期就用給的，沒給就用今天
        caught.setCaughtDate(caughtDate != null ? caughtDate : LocalDate.now());
    }
}
