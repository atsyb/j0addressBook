package service;

import entity.Contact;

import java.util.Scanner;

public interface IContactService {
    int FIELD_NAME = 1;
    int FIELD_SURNAME = 2;
    int FIELD_PHONENUMBER = 3;
    int FIELD_EMAIL = 4;
    int FIELD_BIRTHDAY = 5;
    /** create Contact
     * @param scanner
     */
    public Contact createContact(Scanner scanner);

    public Contact addContact(Scanner scanner);

    /** get Contact
     * @param scanner Contact id
     */
    public Contact getContact(Scanner scanner);

    /**
     * @param scanner  Contact Name
     */
    public void showContactByName(Scanner scanner);

    /**
     * @param scanner id
     * @return contact person
     */
    public Contact alterContact(Scanner scanner);


    /**
     * show All Contact
     */
    public void showAllContact();

    /** del Contact By Id
     * @param scanner id
     */
    public void delContactById(Scanner scanner);


}
