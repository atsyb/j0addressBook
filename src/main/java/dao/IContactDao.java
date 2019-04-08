package dao;

import entity.Contact;

public interface IContactDao {

    public void saveContact(Contact contact);

    public Contact getContactById(int contactId);

    public Contact getContactByName(String contactName);

    public void saveContactNameById(int contactId, String contactName);

    public void saveContactSurNameById(int contactId, String contactSurName);

    public void saveContactPhoneNumberById(int contactId, String contactPhoneNumber);

    public void getAllContact();

    public void deleteContactById(int contactId);

    public void deleteContactByEntity(Contact contact);


}
