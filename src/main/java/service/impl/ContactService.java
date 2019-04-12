package service.impl;

import dao.impl.ContactDao;
import entity.Contact;
import service.IContactService;

import java.util.Objects;
import java.util.Scanner;

public class ContactService implements IContactService {

    private ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Contact createContact(Scanner scanner) {
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

    @Override
    public Contact addContact(Scanner scanner) {
        Contact contact = createContact(scanner);
        contactDao.saveContact(contact);
        System.out.println("Thank you for saving your new contact in this contact book.");
        return contact;
    }

    @Override
    public Contact getContact(Scanner scanner) {
        System.out.println("Enter please ID of your contact person for SHOW:");
        int id = scanner.nextInt();
        return contactDao.getContactById(id);
    }

    @Override
    public void showContactByName(Scanner scanner) {
        System.out.println("Enter please name of your contact person:");
        String name = scanner.next();
        contactDao.getContactByName(name);
    }

    @Override
    public Contact alterContact(Scanner scanner) {
        Contact contact = getContact(scanner);
        int id = contact.getId();
        if (Objects.nonNull(contact)) {
            contact = createContact(scanner);
            return contactDao.saveContactById(contact, id);
        } else {
            System.out.println("Contact not found!");
            return null;
        }
    }

    @Override
    public void editContactNameById(Scanner scanner) {
        System.out.println("Enter please ID of your contact person for EDIT name:");
        int id = scanner.nextInt();
        System.out.println("Enter please name of your contact person:");
        String name = scanner.next();
        contactDao.saveContactNameById(id, name);
    }

    @Override
    public void editContactSurNameById(Scanner scanner) {
        System.out.println("Enter please ID of your contact person for EDIT sur name:");
        int id = scanner.nextInt();
        System.out.println("Enter please sur name of your contact person:");
        String surName = scanner.next();
        contactDao.saveContactSurNameById(id, surName);
    }

    @Override
    public void editContactPhoneNumberById(Scanner scanner) {
        System.out.println("Enter please ID of your contact person for EDIT phone number:");
        int id = scanner.nextInt();
        System.out.println("Enter please phone number of your contact person:");
        String phoneNumber = scanner.next();
        contactDao.saveContactPhoneNumberById(id, phoneNumber);
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
    public void delContactByEntity() {
    }


}
