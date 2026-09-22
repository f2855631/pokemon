package com.example.pokemon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 對應資料庫的 pokemon 資料表，圖鑑資料，唯讀（不會被使用者修改）
@Entity
@Table(name = "pokemon")
@Data // 自動產生 getter/setter/toString/equals/hashCode
@NoArgsConstructor // 自動產生空建構子 Pokemon()
@AllArgsConstructor // 自動產生全欄位建構子
public class Pokemon {

    @Id // 主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 由資料庫自動遞增產生
    private Long id;

    /**
     * 原始圖鑑編號，例如 "0001"
     */
    private String pokedexNumber;

    private String name;

    /**
     * 屬性，多個以逗號分隔，例如 "草,毒"
     */
    private String types;

    private String imageUrl;

    /**
     * 形態類型，例如 "mega"、"gmax"；基礎形態則為 null
     */
    private String formType;

    /**
     * 形態名稱，例如 "超極巨化"；沒有特別形態名稱則為 null
     */
    private String formName;
}
