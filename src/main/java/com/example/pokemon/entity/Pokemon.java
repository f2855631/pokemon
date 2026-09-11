package com.example.pokemon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pokemon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
