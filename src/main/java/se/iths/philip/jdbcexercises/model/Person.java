package se.iths.philip.jdbcexercises.model;

import java.sql.Date;

public class Person {
    private Integer person_id;
    private String first_name;
    private String last_name;
    private Date dob;
    private double income;

    public Person() {
    }

    public Person(Integer person_id, String first_name, String last_name, Date dob, double income) {
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.income = income;
    }

    public Person(String first_name, String last_name, Date dob, double income) {
        this(null, first_name, last_name, dob, income);
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Date getDob() {
        return dob;
    }

    public double getIncome() {
        return income;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID " + person_id +
                ": '" + first_name + '\'' +
                " - '" + last_name + '\'' +
                " - " + dob +
                " - " + income +
                '}';
    }
}
