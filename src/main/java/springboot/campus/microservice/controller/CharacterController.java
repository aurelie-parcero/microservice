package springboot.campus.microservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.campus.microservice.dao.CharacterDao;
import springboot.campus.microservice.model.Character;

import java.util.List;

@Api(tags = {"Character Manager"}, description = "Manage all your characters")
@RestController
public class CharacterController {


    @Autowired
    private CharacterDao characterDao;

    //characters
    @ApiOperation(value = "Characters list", notes = "Get all the characters created", nickname = "charactersIndex")
    @GetMapping(value = "characters")
    public List<Character> index() {

        return characterDao.findAll();
    }

    //characters/{id}
    @ApiOperation(value = "Get a product by ID")
    @GetMapping(value = "characters/{id}")
    public Character getCharacterById(@PathVariable int id) {

        return characterDao.findById(id);
    }

    @ApiOperation(value = "Create a new character")
    @PostMapping(value = "characters")
    public void createCharacter(@RequestBody Character character) {

        characterDao.save(character);

    }

    @ApiOperation(value = "Update character data")
    @PutMapping(value = "characters/{id}")
    public void editCharacter(@PathVariable int id, @RequestBody Character character) {

        characterDao.update(id, character);

    }

    @ApiOperation(value = "Delete a character with its ID")
    @DeleteMapping(value = "characters/{id}")
    public void deleteCharacter(@PathVariable int id) {

        characterDao.delete(id);

    }

}
