package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Contact {
    private int id;
    private String name;
    private String surName;
    private String phoneNumber;
    private int age;
    private double height;
    private boolean married;
    private LocalDate dateOfCreation;

    public Contact() {
    }

    public Contact(String name, String surName, String phoneNumber, int age, double height, boolean married, LocalDate dateOfCreation) {
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.height = height;
        this.married = married;
        this.dateOfCreation = dateOfCreation;
    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public double getHeight() { return height; }

    public void setHeight(double height) { this.height = height; }

    public boolean isMarried() { return married; }

    public void setMarried(boolean married) { this.married = married; }

    public LocalDate getDateOfCreation() { return dateOfCreation; }

    public void setDateOfCreation(LocalDate dateOfCreation) { this.dateOfCreation = dateOfCreation; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                age == contact.age &&
                Double.compare(contact.height, height) == 0 &&
                married == contact.married &&
                Objects.equals(name, contact.name) &&
                Objects.equals(surName, contact.surName) &&
                Objects.equals(phoneNumber, contact.phoneNumber) &&
                Objects.equals(dateOfCreation, contact.dateOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, phoneNumber, age, height, married, dateOfCreation);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", married=" + married +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }

}
