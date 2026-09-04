package com.example.pokemon.controller;

import com.example.pokemon.service.CaughtPokemonService;
import com.example.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class CaughtPokemonController {

    private final CaughtPokemonService caughtPokemonService;
    private final PokemonService pokemonService;

    @GetMapping("/caught")
    public String list(Model model) {
        model.addAttribute("caughtList", caughtPokemonService.findAll());
        return "caught/list";
    }

    @GetMapping("/caught/new")
    public String newForm(@RequestParam(required = false) Long pokemonId, Model model) {
        model.addAttribute("caught", null);
        model.addAttribute("selectedPokemonId", pokemonId);
        model.addAttribute("allPokemons", pokemonService.findAll());
        return "caught/form";
    }

    @GetMapping("/caught/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        var caught = caughtPokemonService.findById(id);
        model.addAttribute("caught", caught);
        model.addAttribute("selectedPokemonId", caught.getPokemon().getId());
        model.addAttribute("allPokemons", pokemonService.findAll());
        return "caught/form";
    }

    @PostMapping("/caught")
    public String create(@RequestParam Long pokemonId,
                          @RequestParam(required = false) String nickname,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate caughtDate) {
        caughtPokemonService.create(pokemonId, nickname, caughtDate);
        return "redirect:/caught";
    }

    @PostMapping("/caught/{id}")
    public String update(@PathVariable Long id,
                          @RequestParam Long pokemonId,
                          @RequestParam(required = false) String nickname,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate caughtDate) {
        caughtPokemonService.update(id, pokemonId, nickname, caughtDate);
        return "redirect:/caught";
    }

    @PostMapping("/caught/{id}/delete")
    public String delete(@PathVariable Long id) {
        caughtPokemonService.deleteById(id);
        return "redirect:/caught";
    }
}
