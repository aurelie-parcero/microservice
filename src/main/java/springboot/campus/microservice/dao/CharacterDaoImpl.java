package springboot.campus.microservice.dao;

import org.springframework.stereotype.Repository;
import springboot.campus.microservice.enums.TypeList;
import springboot.campus.microservice.model.Character;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CharacterDaoImpl implements CharacterDao {

    public static List<Character> characters = new ArrayList<>();

    static {
        characters.add(new Character(1, "Kevin", TypeList.Wizard));
        characters.add(new Character(2, "Morgan", TypeList.Warrior));
        characters.add(new Character(3, "Brian", TypeList.Wizard));
        characters.add(new Character(4, "Marvin", TypeList.Warrior));
    }

    @Override
    public List<Character> findAll() {
        return characters;
    }

    @Override
    public Character findById(int id) {

        for (Character character : characters) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }

    @Override
    public Character save(Character character) {
        character.setId(characters.size() + 1);
        characters.add(character);
        return character;
    }

    @Override
    public Character update(int id, Character character) {

        for (Character savedCharacter : characters) {
            if (savedCharacter.getId() == id) {
                savedCharacter.setId(character.getId());
                savedCharacter.setName(character.getName());
                savedCharacter.setType(character.getType());
                return savedCharacter;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        characters.removeIf(character -> character.getId() == id);
    }
}
