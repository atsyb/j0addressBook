package service;

import entity.Contact;
import exceptions.ExceptionsAddressBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public interface IContactService {
    int FIELD_ID = 0;
    int FIELD_NAME = 1;
    int FIELD_SURNAME = 2;
    int FIELD_PHONENUMBER = 3;
    int FIELD_AGE = 4;
    int FIELD_HEIGHT = 5;
    int FIELD_MARRIED = 6;
    int FIELD_CRE_DATE = 7;

    /**
     * create Contact
     *
     * @param reader createContact
     */
    Contact createContact(BufferedReader reader) throws ExceptionsAddressBook, IOException;

    Contact addContact(BufferedReader reader) throws ExceptionsAddressBook, IOException;

    /**
     * get Contact
     *
     * @param reader Contact id
     */
    Contact getContact(BufferedReader reader) throws ExceptionsAddressBook, IOException, SQLException;

    /**
     * get Contact
     *
     * @param reader Contact Name
     */
    Contact showContactByName(BufferedReader reader) throws IOException;

    /**
     * @param reader id
     * @return contact person
     */
    Contact alterContact(BufferedReader reader) throws ExceptionsAddressBook, IOException, SQLException;

    /**
     * show All Contact
     */
    void showAllContact();

    /**
     * del Contact By Id
     *
     * @param reader id
     */
    void delContactById(BufferedReader reader) throws ExceptionsAddressBook, IOException;

}
