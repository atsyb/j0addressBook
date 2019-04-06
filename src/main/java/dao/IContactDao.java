package dao;

import entity.Contact;

public interface IContactDao {

    public void saveContact(Contact contact);

    public Contact getContactById(int contactId);

    public void getContactByName(String contactName);

    public Contact updateContactById(int contactId, Contact contact);

    public void updateContactByEntity(Contact contact);

    public void showAllContact();

    public void deleteContactById(int contactId);

    public void deleteContactByEntity(Contact contact);


}
