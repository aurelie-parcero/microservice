package springboot.campus.microservice.dao;

import org.springframework.stereotype.Repository;
import springboot.campus.microservice.enums.TypeList;
import springboot.campus.microservice.model.Character;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CharacterDaoImpl implements CharacterDao {

//    public static List<Character> characters = new ArrayList<>();

//    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/dungeonsanddragons", "root", ""
//            );
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from characters");
//
//            while (rs.next()) {
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//            }
//
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

//    static {
//        characters.add(new Character(1, "Kevin", TypeList.Wizard));
//        characters.add(new Character(2, "Morgan", TypeList.Warrior));
//        characters.add(new Character(3, "Brian", TypeList.Wizard));
//        characters.add(new Character(4, "Marvin", TypeList.Warrior));
//    }

    @Override
    public List<Character> findAll() {
        ArrayList<Character> characList = new ArrayList<>();
        try {
            Connection con = JDBCConnection.getInstance();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from characters");

            while (rs.next()) {
                Character character = new Character(rs.getInt("id"), rs.getString("name"), TypeList.valueOf(rs.getString("type")));
                characList.add(character);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return characList;
    }

    @Override
    public Character findById(int id) {
        Character character = new Character();
        try {
//            PreparedStatement stmt = con.prepareStatement("select * from characters where id = ?");
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.execute();
            Connection con = JDBCConnection.getInstance();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from characters where id = " + id);
            while (rs.next()) {
                character.setId(rs.getInt("id"));
                character.setName(rs.getString("name"));
                character.setType(TypeList.valueOf(rs.getString("type")));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return character;
    }

    @Override
    public void save(Character character) {
        String name = character.getName();
        TypeList type = character.getType();
        try {
            Connection con = JDBCConnection.getInstance();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO characters(NAME,TYPE) " + "VALUES ('" + name + "','" + type + "')");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Character update(int id, Character character) {
        String name = character.getName();
        TypeList type = character.getType();
        try {
            Connection con = JDBCConnection.getInstance();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE characters " + "SET NAME = '" + name + "', TYPE = '" + type + "' " + "WHERE id = " + id);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return character;
    }

    @Override
    public void delete(int id) {
        try {
            Connection con = JDBCConnection.getInstance();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM characters WHERE id = " + id);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
