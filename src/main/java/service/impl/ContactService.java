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

    @Override
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

    @Override
    public void showContactById(Scanner scanner) {
        System.out.println("Enter please ID of your contact person for SHOW:");
        int id = scanner.nextInt();
        System.out.println(contactDao.getContactById(id) == null ? "" : contactDao.getContactById(id).toString());
    }

    @Override
    public void showContactByName(Scanner scanner) {
        System.out.println("Enter please name of your contact person:");
        String name = scanner.next();
        contactDao.getContactByName(name);
    }

    @Override
    public void editContactById(Scanner scanner) {
        System.out.println("Enter please ID of your contact person for EDIT:");
        int id = scanner.nextInt();
        contactDao.updateContactById(id, getContactByEntity(scanner));
    }
    
    @Override
    public void showAllContact() {
        contactDao.getAllContact();
    }

    @Override
    public void delContactById(Scanner scanner) {
        System.out.println("Enter please ID of your contact person for DEL:");
        int id = scanner.nextInt();
        contactDao.deleteContactById(id);
    }

    @Override
    public void delContactByEntity(Scanner scanner) {
    }

    @Override
    public Contact getContactByEntity(Scanner scanner) {
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
        return contact;
    }

}
