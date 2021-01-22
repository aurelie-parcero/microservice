package springboot.campus.microservice.model;

import springboot.campus.microservice.enums.TypeList;

import javax.persistence.*;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypeList type;

    public Character() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeList getType() {
        return type;
    }

    public void setType(TypeList type) {
        this.type = type;
    }

    public Character(int id, String name, TypeList type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}

