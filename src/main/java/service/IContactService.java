package service;

import entity.Contact;

import java.util.Scanner;

public interface IContactService {

    public void addContact(Scanner scanner);

    public void showContactById(Scanner scanner);

    public void showContactByName(Scanner scanner);

    public void editContactById(Scanner scanner);

    public void showAllContact();

    public void delContactById(Scanner scanner);

    public void delContactByEntity(Scanner scanner);

    public Contact getContactByEntity(Scanner scanner);
}
