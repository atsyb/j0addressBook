package dao;

import entity.Contact;
import exceptions.ExceptionsAddressBook;

import java.sql.Connection;

public interface IContactDao {

    /**
     * save
     *
     * @param contact person
     */
    Contact saveContact(Contact contact) throws ExceptionsAddressBook;


    /**
     * @param contactName Name
     * @return contact
     */
    Contact getContactByName(String contactName);

    /**
     * @param contactId contact ID for select
     * @return contact
     * @throws ExceptionsAddressBook Exceptions
     */
    Contact getContactById(int contactId) throws ExceptionsAddressBook;

    /**
     * @param contactId contact ID for delete
     * @return contact
     * @throws ExceptionsAddressBook Exceptions
     */
    Contact deleteContactById(int contactId) throws ExceptionsAddressBook;

    /**
     * Print the entire array
     */
    void getAllContact();

}
