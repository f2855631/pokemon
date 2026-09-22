package com.example.pokemon.repository;

import com.example.pokemon.entity.CaughtPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

// 用 interface（不是 class）：Spring Data JPA 只會幫 interface 自動生成實作，
// 寫成 class 反而編譯不過（JpaRepository 本身也是 interface，要用 extends 不是 implements）
// <CaughtPokemon, Long>：第一個是這個 Repository 要操作哪個 Entity，
// 第二個要跟 CaughtPokemon 裡 @Id 欄位的型別一致（那邊是 Long，這邊就填 Long）
public interface CaughtPokemonRepository extends JpaRepository<CaughtPokemon, Long> {
    // 目前不需要自訂查詢方法，繼承來的 save/findById/findAll/deleteById...等
    // 基本 CRUD 已經夠用；之後若要加自訂查詢，直接在這裡加方法簽名即可（不用寫實作）
}
