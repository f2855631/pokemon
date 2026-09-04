package com.example.pokemon.controller;

import com.example.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/")
    public String index() {
        return "redirect:/caught";
    }

    @GetMapping("/pokemons")
    public String list(Model model) {
        model.addAttribute("pokemons", pokemonService.findAll());
        return "pokemon/list";
    }
}
