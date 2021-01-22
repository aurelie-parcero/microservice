package springboot.campus.microservice.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.campus.microservice.enums.TypeList;
import springboot.campus.microservice.model.Character;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {


    List<Character> findAllByType(TypeList type);

}
