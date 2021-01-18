package springboot.campus.microservice.dao;
import org.springframework.stereotype.Repository;
import springboot.campus.microservice.model.Character;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CharacterDaoImpl implements CharacterDao{

    public static List<Character> characters = new ArrayList<>();

    static {
        characters.add(new Character(1, "Kevin", "Wizard"));
        characters.add(new Character(2, "Morgan", "Warrior"));
        characters.add(new Character(3, "Brian", "Wizard"));
    }
    @Override
    public List<Character> findAll() {
        return characters;
    }

    @Override
    public Character findById(int id) {
        return null;
    }

    @Override
    public Character save(Character character) {
        return null;
    }
}
