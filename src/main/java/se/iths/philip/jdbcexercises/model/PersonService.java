package se.iths.philip.jdbcexercises.model;

import se.iths.philip.jdbcexercises.dao.PersonDAO;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> getPersonWithIncomeAbove(double limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Inkomstgräns kan inte vara negativ.");
        }

        List<Person> all = personDAO.findAll();
        List<Person> result = new ArrayList<>();

        for (Person p : all) {
            if (p.getIncome() >= limit) {
                result.add(p);
            }
        }
        return result;
    }

    public Person getByID(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id måste vara större än 0.");
        }
        Person p = personDAO.findByID(id);

        if (p == null) {
            throw new IllegalArgumentException("ingen person hittades med id=" + id);
        }
        return p;
    }

    public void addPerson(Person p) {
        if (p.getFirst_name() == null || p.getFirst_name().isBlank()) {
            throw new IllegalArgumentException("Förnamn måste anges.");
        } else if (p.getLast_name() == null || p.getLast_name().isBlank()) {
            throw new IllegalArgumentException("Efternamn måste anges.");
        } else if (p.getDob() == null) {
            throw new IllegalArgumentException("Födelsedatum måste anges.");
        } else if (p.getIncome() < 0) {
            throw new IllegalArgumentException("Inkomst kan inte vara negativ.");
        }
        personDAO.insert(p);
    }

    public void updatePerson(Person p) {
        if (p.getPerson_id() == null || p.getPerson_id() <= 0) {
            throw new IllegalArgumentException("Id måste vara större än 0 vid uppdatering.");
        } else if (p.getIncome() < 0) {
            throw new IllegalArgumentException("Inkomst kan inte vara negativ.");
        }
        personDAO.update(p);
    }

    public void removePerson(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id måste vara större än 0.");
        }
        Person existing = personDAO.findByID(id);
        if (existing == null) {
            throw new IllegalArgumentException("Ingen person med id=" + id + " att ta bort.");
        }
        personDAO.delete(id);
    }
}
