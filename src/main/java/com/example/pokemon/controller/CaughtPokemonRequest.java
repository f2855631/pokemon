package com.example.pokemon.controller;

import lombok.Data;

import java.time.LocalDate;

/**
 * 新增/更新收服紀錄時，前端傳來的 JSON 會對應到這個物件（Request DTO）。
 * @Data 由 Lombok 在編譯期自動產生 getter/setter/toString/equals/hashCode 與無參數建構子。
 */
@Data
public class CaughtPokemonRequest {

    private Long pokemonId;   // 要收服的寶可夢 id

    private String nickname;  // 玩家自訂暱稱

    private LocalDate caughtDate; // 收服日期
}
