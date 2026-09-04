package com.example.pokemon.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 對應 https://github.com/f2855631/pokemon-crawler 的 pokemon_data.json 單筆結構
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonJsonDto {

    private String id;

    @JsonProperty("sub_id")
    private int subId;

    private String name;

    private String image;

    private List<String> types;
}
