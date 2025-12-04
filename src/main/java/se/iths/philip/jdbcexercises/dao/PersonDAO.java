package se.iths.philip.jdbcexercises.dao;

import se.iths.philip.jdbcexercises.model.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> findAll();

    Person findByID(int id);

    void insert(Person person);

    void update(Person person);

    void delete(int id);
}
