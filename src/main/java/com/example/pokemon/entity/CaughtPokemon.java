package com.example.pokemon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 收服紀錄：對應資料庫的 caught_pokemon 表，一筆代表玩家收服了一隻寶可夢。
 */
@Entity                              // 這個 class 對應資料庫的一張表
@Table(name = "caught_pokemon")      // 表名叫 caught_pokemon
@Data                                 // 自動生成 getter/setter/toString/equals/hashCode
@NoArgsConstructor                    // 空建構子，JPA 讀資料庫重建物件時需要
@AllArgsConstructor                   // 全欄位建構子，方便手動建立完整物件
public class CaughtPokemon {

    @Id                                                  // 主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 交給資料庫自動遞增產生
    private Long id;

    // 多對一：很多筆收服紀錄可以指向同一隻圖鑑寶可夢
    @ManyToOne
    @JoinColumn(name = "pokemon_id")  // 資料庫實際的外鍵欄位叫 pokemon_id
    private Pokemon pokemon;

    private String nickname;   // 玩家自訂暱稱

    private LocalDate caughtDate;  // 收服日期
}
