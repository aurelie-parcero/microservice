package springboot.campus.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.campus.microservice.dao.CharacterDao;
import springboot.campus.microservice.model.Character;

import java.util.List;

@RestController
public class CharacterController {


    @Autowired
    private CharacterDao characterDao;

    //characters
    @GetMapping(value = "characters")
    public List<Character> index() {

        return characterDao.findAll();
    }

    //characters/{id}
    @GetMapping(value = "characters/{id}")
    public Character getCharacterById(@PathVariable int id) {

        return new Character(id, "Kévin", "Wizard");
    }

}
