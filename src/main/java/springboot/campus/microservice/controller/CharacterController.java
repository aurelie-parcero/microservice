package springboot.campus.microservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springboot.campus.microservice.Character;

@RestController
public class CharacterController {
    //character/{id}
    @GetMapping(value = "character/{id}")
    public Character displayCharacter(@PathVariable int id) {

        return new Character(id, new String("Kévin"), "Wizard");
    }

    //characters
    @GetMapping(value = "characters")
    public String index() {

        return "Hello World";
    }
}
