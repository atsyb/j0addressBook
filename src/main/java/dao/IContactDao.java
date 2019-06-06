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
    void saveContact(Contact contact) throws ExceptionsAddressBook;


    /**
     * @param contactName Name
     * @return contact
     */
    Contact getContactByName(String contactName);


    /**
     * @param contact contact for insert
     * @param conn DB
     * @return contact
     * @throws ExceptionsAddressBook Exceptions
     */
    Contact insertContact(Contact contact, Connection conn) throws ExceptionsAddressBook;

    /**
     * @param contactId contact ID for select
     * @param conn DB
     * @return contact
     * @throws ExceptionsAddressBook Exceptions
     */
    Contact selectContactById(int contactId, Connection conn) throws ExceptionsAddressBook;

    /**
     * @param contact contact for update
     * @param contactId contact ID
     * @param conn DB
     * @return contact
     * @throws ExceptionsAddressBook Exceptions
     */
    Contact updateContactById(Contact contact, int contactId, Connection conn) throws ExceptionsAddressBook;

    /**
     * @param contactId contact ID for delete
     * @param conn DB
     * @return contact
     * @throws ExceptionsAddressBook Exceptions
     */
    Contact deleteContactById(int contactId, Connection conn) throws ExceptionsAddressBook;

    /**
     * Print the entire array
     */
    void getAllContact();

}
