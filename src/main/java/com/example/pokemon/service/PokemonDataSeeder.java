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
@Slf4j // 自動產生一個叫 log 的物件，可以用 log.info()、log.warn() 寫日誌
@Component
@RequiredArgsConstructor
public class PokemonDataSeeder implements CommandLineRunner { // 實作這個介面，run() 會在應用程式啟動完成後自動被呼叫一次

    private static final String JSON_URL =
            "https://raw.githubusercontent.com/f2855631/pokemon-crawler/main/pokemon_data.json";
    private static final String IMAGE_BASE_URL =
            "https://raw.githubusercontent.com/f2855631/pokemon-crawler/main/";

    private final PokemonRepository pokemonRepository;
    private final ObjectMapper objectMapper = new ObjectMapper(); // 負責把 JSON 字串轉成 Java 物件

    @Override
    public void run(String... args) throws Exception {
        // 資料庫已經有資料，代表匯入過了，不用重複匯入
        if (pokemonRepository.count() > 0) {
            log.info("Pokemon 資料表已有資料，略過種子資料匯入");
            return;
        }

        // 用 HTTP GET 把整份 JSON 抓下來（純文字字串）
        String json = RestClient.create()
                .get()
                .uri(JSON_URL)
                .retrieve()
                .body(String.class);

        if (json == null) {
            log.warn("無法從 {} 取得寶可夢資料", JSON_URL);
            return;
        }

        // 把 JSON 字串解析成一筆一筆的 PokemonJsonDto 物件
        List<PokemonJsonDto> dtoList = objectMapper.readValue(json, new TypeReference<List<PokemonJsonDto>>() {
        });

        // 把每一筆 DTO 轉成要存進資料庫的 Entity
        List<Pokemon> pokemons = dtoList.stream()
                .map(this::toEntity)
                .toList();

        pokemonRepository.saveAll(pokemons); // 一次寫入所有資料
        log.info("已匯入 {} 筆寶可夢種子資料", pokemons.size());
    }

    // 把抓下來的 DTO 轉換成資料庫要存的 Entity 格式
    private Pokemon toEntity(PokemonJsonDto dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokedexNumber(dto.getId());
        pokemon.setName(dto.getName());
        pokemon.setTypes(String.join(",", dto.getTypes())); // 把屬性陣列合併成逗號分隔的字串
        pokemon.setImageUrl(IMAGE_BASE_URL + dto.getImage()); // 拼出完整的圖片網址
        pokemon.setFormType(blankToNull(dto.getFormType()));
        pokemon.setFormName(blankToNull(dto.getFormName()));
        return pokemon;
    }

    // 原始資料裡空字串跟沒有值意義一樣，統一轉成 null 比較乾淨
    private String blankToNull(String value) {
        return (value == null || value.isBlank()) ? null : value;
    }
}
