package service;

import entity.Contact;

import java.util.Scanner;

public interface IContactService {

    /**
     * @param scanner
     */
    public void addContact(Scanner scanner);

    /**
     * @param scanner
     */
    public void showContactById(Scanner scanner);

    /**
     * @param scanner
     */
    public void showContactByName(Scanner scanner);

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
     * @param scanner
     * not used in service
     */
    public void delContactByEntity(Scanner scanner);

}
