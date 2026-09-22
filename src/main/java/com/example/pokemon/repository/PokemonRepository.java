package com.example.pokemon.repository;

import com.example.pokemon.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

// 繼承 JpaRepository 就自動擁有 findAll()、findById()、save()、deleteById() 等基本 CRUD 方法
// 不用自己寫任何 SQL 或實作內容，Spring Data JPA 會在背後自動產生
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
