package springboot.campus.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import springboot.campus.microservice.enums.TypeList;
import springboot.campus.microservice.model.Character;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${name.not.empty}")
    private String nameErrorMessage;

    @Value("${type.not.empty}")
    private String typeErrorMessage;

    @GetMapping(value = "/")
    public String getTemplate(@RequestParam(name = "name", required = false, defaultValue = "Player") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping(value = "/characters")
    public String characters(Model model) {
        String url = "http://127.0.0.1:8081/characters";
        List<Character> characters = restTemplate.getForObject(url, List.class);
        model.addAttribute("characters", characters);
        return "characters";
    }

    @RequestMapping(value = "/characters", params = "type")
    public String charactersByType(Model model, @RequestParam String type) {
        String url = "http://127.0.0.1:8081/characters?type=" + type;
        List<Character> characters = restTemplate.getForObject(url, List.class, type);
        model.addAttribute("characters", characters);
        model.addAttribute("type", type);
        return "characters";
    }

    @GetMapping(value = "character/{id}")
    public String character(@PathVariable int id, Model model) {
        String url = "http://127.0.0.1:8081/characters/" + id;
        Character character = restTemplate.getForObject(url, Character.class);
        model.addAttribute("character", character);
        return "character";
    }

    @GetMapping(value = "characters/new")
    public String register(Model model) {
        model.addAttribute("character", new Character());
        return "new";
    }

    @PostMapping(value = "characters/new")
    public String create(Model model, @ModelAttribute Character character) {
        String url = "http://127.0.0.1:8081/characters";
        String name = character.getName();
        TypeList type = character.getType();

        if (name == null || name.length() == 0) {

            model.addAttribute("nameErrorMessage", nameErrorMessage);
            return "new";
        }
        if (type == null) {
            model.addAttribute("typeErrorMessage", typeErrorMessage);
            return "new";
        }
        restTemplate.postForObject(url, character, Character.class);
        return "redirect:/characters";
    }

    @GetMapping(value = "character/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        String url = "http://127.0.0.1:8081/characters/" + id;
        Character character = restTemplate.getForObject(url, Character.class);
        model.addAttribute("character", character);
        return "edit";
    }

    @PostMapping(value = "character/{id}/edit")
    public String update(@PathVariable int id, Model model, @ModelAttribute Character character) {
        String url = "http://127.0.0.1:8081/characters/" + id;
        String name = character.getName();
        TypeList type = character.getType();

        if (name == null || name.length() == 0) {

            model.addAttribute("nameErrorMessage", nameErrorMessage);
            return "edit";
        }
        if (type == null) {
            model.addAttribute("typeErrorMessage", typeErrorMessage);
            return "edit";
        }
        restTemplate.put(url, character, Character.class);
        return "redirect:/character/{id}";
    }

    @GetMapping(value = "character/{id}/delete")
    public String deleteCharacter(@PathVariable int id) {
        String url = "http://127.0.0.1:8081/characters/" + id;
        restTemplate.delete(url, id);
        return "redirect:/characters";
    }

}
