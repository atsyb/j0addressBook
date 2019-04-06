package dao.impl;

import dao.IContactDao;
import entity.Contact;

public class ContactDao implements IContactDao {

    public static int generator = 0;

    Contact[] store = new Contact[10];

    public void saveContact(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] == null) {
                contact.setId(++generator);
                store[argument] = contact;
                System.out.println("This contact was added in your contact book");
                System.out.println(contact.toString());
                break;
            }
        }
    }

    public Contact getContactById(int contactId) {
        if (store[contactId] != null) {
            System.out.println(store[contactId].toString());
        } else {
            System.out.println("There is no contact with ID=" + contactId);
        }
        return store[contactId];
    }

    public void getContactByName(String contactName) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument].getName() == contactName) {
                System.out.println(store[argument].toString());
            }
        }
    }

    public Contact updateContactById(int contactId, Contact contact) {
        if (store[contactId] != null) {
            store[contactId] = contact;
            System.out.println("This contact was changed");
        } else {
            System.out.println("Contact for change not found");
        }
        return contact;
    }

    public void updateContactByEntity(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].equals(contact)) {
                store[argument] = contact;
                System.out.println("This contact was changed");
                System.out.println(contact.toString());
                break;
            } else {
                System.out.println("Contact for change not found");
            }
        }
    }

    public void showAllContact() {
        for (Contact storeContact : store) {
            System.out.println(storeContact.toString());
        }
    }

    public void deleteContactById(int contactId) {
        if (store[contactId] != null) {
            System.out.println(store[contactId].toString() + "contact has been deleted");
            store[contactId] = null;
        } else {
            System.out.println("No contact to delete!");
        }
    }

    public void deleteContactByEntity(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].equals(contact)) {
                store[argument] = null;
                System.out.println("This contact was deleted");
                System.out.println(contact.toString());
                break;
            } else {
                System.out.println("Contact for deleted not found");
            }
        }
    }

    public Contact[] getStore() {
        return store;
    }
}

