package com.example.pokemon.service;

import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * 應用程式啟動時，若資料庫是空的，就從 GitHub 上的寶可夢爬蟲專案抓 JSON 匯入種子資料。
 * 基礎形態、Mega 進化、超極巨化等所有變體全部匯入，並保留 formType/formName 供畫面標示區分。
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PokemonDataSeeder implements CommandLineRunner {

    private static final String JSON_URL =
            "https://raw.githubusercontent.com/f2855631/pokemon-crawler/main/pokemon_data.json";
    private static final String IMAGE_BASE_URL =
            "https://raw.githubusercontent.com/f2855631/pokemon-crawler/main/";

    private final PokemonRepository pokemonRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        if (pokemonRepository.count() > 0) {
            log.info("Pokemon 資料表已有資料，略過種子資料匯入");
            return;
        }

        String json = RestClient.create()
                .get()
                .uri(JSON_URL)
                .retrieve()
                .body(String.class);

        if (json == null) {
            log.warn("無法從 {} 取得寶可夢資料", JSON_URL);
            return;
        }

        List<PokemonJsonDto> dtoList = objectMapper.readValue(json, new TypeReference<List<PokemonJsonDto>>() {
        });

        List<Pokemon> pokemons = dtoList.stream()
                .map(this::toEntity)
                .toList();

        pokemonRepository.saveAll(pokemons);
        log.info("已匯入 {} 筆寶可夢種子資料", pokemons.size());
    }

    private Pokemon toEntity(PokemonJsonDto dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokedexNumber(dto.getId());
        pokemon.setName(dto.getName());
        pokemon.setTypes(String.join(",", dto.getTypes()));
        pokemon.setImageUrl(IMAGE_BASE_URL + dto.getImage());
        pokemon.setFormType(blankToNull(dto.getFormType()));
        pokemon.setFormName(blankToNull(dto.getFormName()));
        return pokemon;
    }

    private String blankToNull(String value) {
        return (value == null || value.isBlank()) ? null : value;
    }
}
