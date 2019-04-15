package dao;

import entity.Contact;

public interface IContactDao {

    /** save
     * @param contact person
     */
    void saveContact(Contact contact);

    /** get
     * @param contactId
     * @return
     */
    Contact getContactById(int contactId);

    /**
     * @param contactName
     * @return
     */
    Contact getContactByName(String contactName);


    /**
     * @param contact
     * @return
     */
    Contact updateContact(Contact contact);

    /**
     * @param contactId
     * @param contactName
     */
    void saveContactNameById(int contactId, String contactName);

    /**
     * @param contactId
     * @param contactSurName
     */
    void saveContactSurNameById(int contactId, String contactSurName);

    /**
     * @param contactId
     * @param contactPhoneNumber
     */
    void saveContactPhoneNumberById(int contactId, String contactPhoneNumber);

    /**
     * Print the entire array
     */
    void getAllContact();

    /**
     * @param contactId
     */
    void deleteContactById(int contactId);

}
