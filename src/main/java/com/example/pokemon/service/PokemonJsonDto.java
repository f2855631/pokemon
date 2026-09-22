package com.example.pokemon.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 對應 https://github.com/f2855631/pokemon-crawler 的 pokemon_data.json 單筆結構
 * 只是用來「暫時裝」抓下來的原始 JSON 資料，不是資料庫的 Entity
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 裡有欄位這裡沒定義也沒關係，直接忽略
public class PokemonJsonDto {

    private String id; // 圖鑑編號

    @JsonProperty("sub_id") // JSON 裡的欄位名稱是底線命名，這裡轉成駝峰式 subId
    private int subId; // 0 代表基礎形態，非 0 代表 Mega/超極巨化等變體

    private String name;

    private String image; // 圖片相對路徑，要另外拼上網域才是完整網址

    private List<String> types;

    @JsonProperty("form_type")
    private String formType;

    @JsonProperty("form_name")
    private String formName;
}
