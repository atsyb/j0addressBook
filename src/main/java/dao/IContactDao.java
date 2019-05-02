package dao;

import entity.Contact;
import exceptions.ExceptionsAddressBook;

public interface IContactDao {

    /** save
     * @param contact person
     */
    void saveContact(Contact contact) throws ExceptionsAddressBook;

    /** get
     * @param contactId id
     * @return contact
     */
    Contact getContactById(int contactId);

    /**
     * @param contactName Name
     * @return contact
     */
    Contact getContactByName(String contactName);


    /**
     * @param contact contact
     * @return contact
     */
    Contact updateContact(Contact contact);

    /**
     * @param contactId id
     * @param contactName Name
     */
    void saveContactNameById(int contactId, String contactName);

    /**
     * @param contactId id
     * @param contactSurName Name
     */
    void saveContactSurNameById(int contactId, String contactSurName);

    /**
     * @param contactId id
     * @param contactPhoneNumber phone
     */
    void saveContactPhoneNumberById(int contactId, String contactPhoneNumber);

    /**
     * Print the entire array
     */
    void getAllContact();

    /**
     * @param contactId id
     */
    void deleteContactById(int contactId);

}
