package se.iths.philip.jdbcexercises;

import se.iths.philip.jdbcexercises.dao.PersonDAO;
import se.iths.philip.jdbcexercises.dao.PersonDAOImpel;
import se.iths.philip.jdbcexercises.model.Person;

import java.util.List;

public class MainVG {
    public static void main(String[] args) {

        PersonDAO dao = new PersonDAOImpel();

        // Insert - Lägger in ny person in i tabellen.
        Person felix = new Person("Felix", "Fredriksson",
                java.sql.Date.valueOf("1990-05-01"), 55000);
        dao.insert(felix);

        // Find All - Visar alla person i tabellen & alla värden i tabellen.
        List<Person> allPersons = dao.findAll();
        System.out.println("Alla personer i databasen:");
        allPersons.forEach(System.out::println);

        // Find by ID 
        System.out.println("\nHämta person med ID 10:");
        Person p = dao.findByID(10);
        System.out.println(p);

        // Update - Uppdaterar personen i tabellen tillsammans med findByID().
        if (p != null) {
            p.setIncome(60000);
            dao.update(p);
        }

        // Delete - Tar bort en person från tabellen med ID.
        dao.delete(7);

        // Skriver ut efter när något har ändrats.
        System.out.println("\nEfter ändringar:");
        dao.findAll().forEach(System.out::println);
    }
}
