package springboot.campus.microservice.dao;

import java.util.List;
import springboot.campus.microservice.model.Character;

public interface CharacterDao {

    public List<Character> findAll();
    public Character findById(int id);
    public Character save(Character character);
}
