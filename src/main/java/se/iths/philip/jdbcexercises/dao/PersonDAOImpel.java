package se.iths.philip.jdbcexercises.dao;

import se.iths.philip.jdbcexercises.AppInfo;
import se.iths.philip.jdbcexercises.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpel implements PersonDAO {

    private Connection getConnection() throws SQLException {
        AppInfo appInfo = AppInfo.getInstance();
        String url = appInfo.getProperties("db.urlMydb");
        String user = appInfo.getProperties("db.userRoot");
        String pass = appInfo.getProperties("db.pass1");

        return DriverManager.getConnection(url, user, pass);
    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT person_id, first_name, last_name, dob, income FROM person";
        List<Person> persons = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Person p = new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("dob"),
                        rs.getDouble("income")
                );
                persons.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public Person findByID(int id) {
        String sql = "SELECT person_id, first_name, last_name, dob, income FROM person WHERE person_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return new Person(
                            rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("dob"),
                            rs.getDouble("income")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Person person) {
        String sql = "INSERT INTO person (first_name, last_name, dob, income) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getFirst_name());
            pstmt.setString(2, person.getLast_name());
            pstmt.setDate(3, person.getDob());
            pstmt.setDouble(4, person.getIncome());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {
        String sql = "UPDATE person SET first_name = ?, last_name = ?, dob = ?, income = ? WHERE person_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getFirst_name());
            pstmt.setString(2, person.getLast_name());
            pstmt.setDate(3, person.getDob());
            pstmt.setDouble(4, person.getIncome());
            pstmt.setInt(5, person.getPerson_id());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM person WHERE person_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
