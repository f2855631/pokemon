package com.example.pokemon.controller;

import lombok.Data;

import java.time.LocalDate;

/**
 * 新增/更新收服紀錄時，前端傳過來的 JSON body 對應的物件。
 */
@Data
public class CaughtPokemonRequest {

    private Long pokemonId;

    private String nickname;

    private LocalDate caughtDate;
}
