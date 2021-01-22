package springboot.campus.microservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.campus.microservice.enums.TypeList;
import springboot.campus.microservice.jpa.exceptions.CharacterNotFoundException;
import springboot.campus.microservice.jpa.repositories.CharacterRepository;
import springboot.campus.microservice.model.Character;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Api(tags = {"Character Manager"}, description = "Manage all your characters")
@RestController
public class CharacterController {


//    @Autowired
//    private CharacterDao characterDao;

    @Autowired
    private CharacterRepository characterRepository;


    @ApiOperation(value = "Characters list", notes = "Get all the characters created", nickname = "charactersIndex")
    @GetMapping("/characters")
    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }

    @ApiOperation(value = "Characters list", notes = "Get all the characters created", nickname = "charactersIndex")
    @RequestMapping(value = "/characters", params = "type")
    public @ResponseBody List<Character> getCharactersByType(@RequestParam(name = "type", required = false, defaultValue = "wizard") String typeList) {
        return characterRepository.findAllByType(TypeList.valueOf(typeList.toUpperCase()));
    }

    @ApiOperation(value = "Get a product by ID")
    @GetMapping("characters/{id}")
    public Character getCharacter(@PathVariable int id) {
        return characterRepository.findById(id).orElse(null);
    }

    @ApiOperation(value = "Create a new character")
    @PostMapping("/characters")
    public Character addCharacter(@RequestBody Character character) {
        return characterRepository.save(character);
    }

    @ApiOperation(value = "Update character data")
    @PutMapping("/characters/{id}")
    public Character updateCharacter(@PathVariable int id, @RequestBody Character character) {
        Character characterToUpdate = characterRepository.findById(id).orElse(null);

        assert characterToUpdate != null;
        characterToUpdate.setName(character.getName());
        characterToUpdate.setType(character.getType());

        return characterRepository.save(characterToUpdate);
    }

    @ApiOperation(value = "Delete a character with its ID")
    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable int id) {
        characterRepository.findById(id);
        characterRepository.deleteById(id);
    }



    // Uses Dao

    //characters
//    @ApiOperation(value = "Characters list", notes = "Get all the characters created", nickname = "charactersIndex")
//    @GetMapping(value = "characters")
//    public List<Character> index() {
//
//        return characterDao.findAll();
//    }
//
//    //characters/{id}
//    @ApiOperation(value = "Get a product by ID")
//    @GetMapping(value = "characters/{id}")
//    public Character getCharacterById(@PathVariable int id) {
//
//        return characterDao.findById(id);
//    }
//
//    @ApiOperation(value = "Create a new character")
//    @PostMapping(value = "characters")
//    public void createCharacter(@RequestBody Character character) {
//
//        characterDao.save(character);
//
//    }
//
//    @ApiOperation(value = "Update character data")
//    @PutMapping(value = "characters/{id}")
//    public void editCharacter(@PathVariable int id, @RequestBody Character character) {
//
//        characterDao.update(id, character);
//
//    }
//
//    @ApiOperation(value = "Delete a character with its ID")
//    @DeleteMapping(value = "characters/{id}")
//    public void deleteCharacter(@PathVariable int id) {
//
//        characterDao.delete(id);
//
//    }

}
