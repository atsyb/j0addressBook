package service.impl;

import dao.impl.ContactDao;
import entity.Contact;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;
import service.IContactService;

import java.util.Objects;
import java.util.Scanner;

import static service.IComandLineService.showMenuEditContact;

public class ContactService implements IContactService {

    private ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Contact createContact(Scanner scanner) {
        Contact contact = new Contact();
        try {
            System.out.println("Enter please name of your contact person:");
            String name = scanner.next();
            contact.setName(name);

            System.out.println("Enter please sur name of your contact person:");
            String surName = scanner.next();
            contact.setSurName(surName);

            System.out.println("Enter please phone number of your contact person");
            String phoneNumber = scanner.next().replaceAll("[^0-9+]", "");
            contact.setPhoneNumber(phoneNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return contact;
    }

    @Override
    public Contact addContact(Scanner scanner) throws ExceptionsAddressBook {
        Contact contact = createContact(scanner);
        contactDao.saveContact(contact);
        System.out.println("Thank you for saving your new contact in this contact book.");
        return contact;
    }

    @Override
    public Contact getContact(Scanner scanner) throws ExceptionsAddressBook {
        System.out.println("Enter please ID of your contact person:");
        if (!scanner.hasNextInt()) {
            throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
        }
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
    public Contact alterContact(Scanner scanner) throws ExceptionsAddressBook {
        Contact contact = getContact(scanner);
        if (Objects.nonNull(contact)) {
            int id = contact.getId();
            System.out.println("ALTER: " + contact.toString());
            contact = modifierFields(scanner, contact);
            return contactDao.saveContactById(contact, id);
        } else {
            System.out.println("Contact for alter not found!");
            return null;
        }
    }

    private Contact modifierFields(Scanner scanner, Contact contact) throws ExceptionsAddressBook {
        showMenuEditContact();
        if (!scanner.hasNextInt()) {
            throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
        }
        int fieldNumber = scanner.nextInt();
        switch (fieldNumber) {
            case FIELD_NAME: {
                System.out.println("     Enter new name:");
                return editContactField(FIELD_NAME, contact, scanner);
            }
            case FIELD_SURNAME: {
                System.out.println("     Enter new sur name:");
                return editContactField(FIELD_SURNAME, contact, scanner);
            }
            case FIELD_PHONENUMBER: {
                System.out.println("     Enter new phone number:");
                return editContactField(FIELD_PHONENUMBER, contact, scanner);
            }
            default: {
                System.out.println("Sorry, nothing to change");
                return contact;
            }
        }
    }

    private Contact editContactField(int fieldId, Contact contact, Scanner scanner) {
        String fieldValue = scanner.next();
        switch (fieldId) {
            case FIELD_NAME: {
                contact.setName(fieldValue);
                break;
            }
            case FIELD_SURNAME: {
                contact.setSurName(fieldValue);
                break;
            }
            case FIELD_PHONENUMBER: {
                contact.setPhoneNumber(fieldValue);
                break;
            }
        }
        return contact;
    }


    @Override
    public void showAllContact() {
        contactDao.getAllContact();
    }

    @Override
    public void delContactById(Scanner scanner) throws ExceptionsAddressBook {
        System.out.println("Enter please ID of your contact person for DEL:");
        if (!scanner.hasNextInt()) {
            throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
        }
        int id = scanner.nextInt();
        contactDao.deleteContactById(id);
    }

}
