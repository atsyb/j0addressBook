package service.impl;

import dao.impl.ContactDao;
import entity.Contact;
import service.IContactService;

import java.util.Scanner;

public class ContactService implements IContactService {

    private ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public void addContact(Scanner scanner) {
        Contact contact = new Contact();

        System.out.println("Enter please name of your contact person:");
        String name = scanner.next();
        contact.setName(name);

        System.out.println("Enter please sur name of your contact person:");
        String surName = scanner.next();
        contact.setSurName(surName);

        System.out.println("Enter please phone number of your contact person");
        String phoneNumber = scanner.next().replaceAll("[^0-9+]", "");
        contact.setPhoneNumber(phoneNumber);

        contactDao.saveContact(contact);

        System.out.println("Thank you for saving your contact in this contact book.");
    }

    public void allContact(){
        contactDao.showAllContact();
    }

    public void delContact(Scanner scanner){
        System.out.println("Enter please ID of your contact person for DEL:");
        int id = scanner.nextInt();
        contactDao.deleteContactById(id);
    }

}
