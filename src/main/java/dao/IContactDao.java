package dao;

import entity.Contact;

public interface IContactDao {

    public void saveContact(Contact contact);

    public Contact getContactById(int contactId);

    public Contact getContactByName(String contactName);

    public Contact updateContactById(int contactId, Contact contact);

    public void getAllContact();

    public void deleteContactById(int contactId);

    public void deleteContactByEntity(Contact contact);


}
