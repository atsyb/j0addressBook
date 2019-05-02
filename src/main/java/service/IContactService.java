package service;

import entity.Contact;
import exceptions.ExceptionsAddressBook;

import java.util.Scanner;

public interface IContactService {
    int FIELD_NAME = 1;
    int FIELD_SURNAME = 2;
    int FIELD_PHONENUMBER = 3;
    int FIELD_EMAIL = 4;
    int FIELD_BIRTHDAY = 5;

    /**
     * create Contact
     *
     * @param scanner createContact
     */
    Contact createContact(Scanner scanner);

    Contact addContact(Scanner scanner) throws ExceptionsAddressBook;

    /**
     * get Contact
     *
     * @param scanner Contact id
     */
    Contact getContact(Scanner scanner) throws ExceptionsAddressBook;

    /**
     * @param scanner Contact Name
     */
    void showContactByName(Scanner scanner);

    /**
     * @param scanner id
     * @return contact person
     */
    Contact alterContact(Scanner scanner) throws ExceptionsAddressBook;


    /**
     * show All Contact
     */
    void showAllContact();

    /**
     * del Contact By Id
     *
     * @param scanner id
     */
    void delContactById(Scanner scanner) throws ExceptionsAddressBook;


}
