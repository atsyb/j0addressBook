package service.impl;

import dao.impl.ContactDao;
import entity.Contact;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;
import service.IContactService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import static service.IComandLineService.showMenuEditContact;

public class ContactService implements IContactService {

    private ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Contact createContact(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        Contact contact = new Contact();

        System.out.println("Enter please name of your contact person:");
        String name = reader.readLine();
        contact.setName(name);

        System.out.println("Enter please phone number of your contact person");
        String phoneNumber = reader.readLine().replaceAll("[^0-9+]", "");
        contact.setPhoneNumber(phoneNumber);

        System.out.println("Want to add fields? [yes(Y)/no(<Enter>)]");
        String wantAddFields = reader.readLine().toLowerCase();
        if (wantAddFields.equals("y")) {

            System.out.println("Enter please sur name of your contact person:");
            String surName = reader.readLine();
            contact.setSurName(surName);

            System.out.println("Enter please age of your contact person:");
            int age = Integer.valueOf(reader.readLine(), 10);
            contact.setAge(age);

            System.out.println("Enter please height of your contact person:");
            double height = Double.parseDouble(reader.readLine());
            contact.setHeight(height); /* "-?\\d+(\\.\\d+)?" */

            System.out.println("Сontact person married? [y/n]");
            String isMarried = reader.readLine().toLowerCase();
            boolean married;
            if (isMarried.equals("y")) {
                married = true;
                contact.setMarried(married);
            } else if (isMarried.equals("n")) {
                married = false;
                contact.setMarried(married);
            } else {
                System.out.println("  Married unknown! Set false.");
                throw new ExceptionsAddressBook(ErrorCode.ENTERED_INTEGER_MARRIED);
            }
        }

        return contact;
    }

    @Override
    public Contact addContact(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        Contact contact = createContact(reader);
        contactDao.saveContact(contact);
        System.out.println("Thank you for saving your new contact in this contact book.");
        return contact;
    }

    @Override
    public Contact getContact(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        System.out.println("Enter please ID of your contact person:");
        //if (!scanner.hasNextInt()) {
        //    throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
        //}
        int id = Integer.valueOf(reader.readLine());
        return contactDao.getContactById(id);
    }

    @Override
    public void showContactByName(BufferedReader reader) throws IOException {
        System.out.println("Enter please name of your contact person:");
        String name = reader.readLine();
        contactDao.getContactByName(name);
    }

    @Override
    public Contact alterContact(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        Contact contact = getContact(reader);
        if (Objects.nonNull(contact)) {
            int id = contact.getId();
            System.out.println("ALTER: " + contact.toString());
            contact = modifierFields(reader, contact);
            return contactDao.saveContactById(contact, id);
        } else {
            System.out.println("Contact for alter not found!");
            return null;
        }
    }

    private Contact modifierFields(BufferedReader reader, Contact contact) throws ExceptionsAddressBook, IOException {
        showMenuEditContact();
        String fieldNumberStr = reader.readLine();
        if (!fieldNumberStr.matches("\\d+")) {
            throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
        }
        int fieldNumber = Integer.valueOf(fieldNumberStr);
        switch (fieldNumber) {
            case FIELD_NAME: {
                System.out.println("     Enter new name:");
                return editContactField(FIELD_NAME, contact, reader);
            }
            case FIELD_SURNAME: {
                System.out.println("     Enter new sur name:");
                return editContactField(FIELD_SURNAME, contact, reader);
            }
            case FIELD_PHONENUMBER: {
                System.out.println("     Enter new phone number:");
                return editContactField(FIELD_PHONENUMBER, contact, reader);
            }
            case FIELD_AGE: {
                System.out.println("     Enter new age:");
                return editContactField(FIELD_AGE, contact, reader);
            }
            case FIELD_HEIGHT: {
                System.out.println("     Enter new height:");
                return editContactField(FIELD_HEIGHT, contact, reader);
            }
            case FIELD_MARRIED: {
                System.out.println("     Enter new married:");
                return editContactField(FIELD_MARRIED, contact, reader);
            }
            default: {
                System.out.println("Sorry, nothing to change");
                return contact;
            }
        }
    }

    private Contact editContactField(int fieldId, Contact contact, BufferedReader reader) throws IOException {
        String fieldValue = reader.readLine();
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
    public void delContactById(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        System.out.println("Enter please ID of your contact person for DEL:");
        try {
            int id = Integer.parseInt(reader.readLine());
            contactDao.deleteContactById(id);
        } catch (NumberFormatException nfe) {
            //throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER.getCodeId()+" ID must be an integer!");
            throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
        }
    }

}
