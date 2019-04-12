package service;

import entity.Contact;

import java.util.Scanner;

public interface IContactService {

    /**
     * @param scanner
     */
    public Contact createContact(Scanner scanner);

    public Contact addContact(Scanner scanner);

    /**
     * @param scanner
     */
    public Contact getContact(Scanner scanner);

    /**
     * @param scanner
     */
    public void showContactByName(Scanner scanner);

    /**
     * @param scanner
     * @return
     */
    public Contact alterContact(Scanner scanner);

    /**
     * @param scanner
     */
    public void editContactNameById(Scanner scanner);

    /**
     * @param scanner
     */
    public void editContactSurNameById(Scanner scanner);

    /**
     * @param scanner
     */
    public void editContactPhoneNumberById(Scanner scanner);

    /**
     *
     */
    public void showAllContact();

    /**
     * @param scanner
     */
    public void delContactById(Scanner scanner);

    /**
     * not used in service
     */
    public void delContactByEntity();

}
