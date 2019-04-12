package dao;

import entity.Contact;

public interface IContactDao {

    /**
     * @param contact
     */
    public void saveContact(Contact contact);

    /**
     * @param contactId
     * @return
     */
    public Contact getContactById(int contactId);

    /**
     * @param contactName
     * @return
     */
    public Contact getContactByName(String contactName);


    /**
     * @param contact
     * @return
     */
    public Contact updateContact(Contact contact);

    /**
     * @param contactId
     * @param contactName
     */
    public void saveContactNameById(int contactId, String contactName);

    /**
     * @param contactId
     * @param contactSurName
     */
    public void saveContactSurNameById(int contactId, String contactSurName);

    /**
     * @param contactId
     * @param contactPhoneNumber
     */
    public void saveContactPhoneNumberById(int contactId, String contactPhoneNumber);

    /**
     * Print the entire array
     */
    public void getAllContact();

    /**
     * @param contactId
     */
    public void deleteContactById(int contactId);

    /**
     * @param contact Used in other methods, for example deleteContactById
     * @see dao.impl.ContactDao
     */
    public void deleteContactByEntity(Contact contact);

}
