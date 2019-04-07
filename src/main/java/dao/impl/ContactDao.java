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
        Contact contact = null;
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].getId() == contactId) {
                contact = store[argument];
                break;
            } else {
                if (argument == store.length - 1) {
                    System.out.println("There is no contact with ID=" + contactId);
                }
            }
        }
        return contact;
    }

    public Contact getContactByName(String contactName) {
        Contact contact = null;
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].getName().equals(contactName)) {
                System.out.println(store[argument].getName());
                contact = store[argument];
                System.out.println(store[argument].toString());
                break;
            } else {
                if (argument == store.length - 1) {
                    System.out.println("Contact name " + contactName + " not found");
                }
            }
        }
        return contact;
    }

    public Contact updateContactById(int contactId, Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].getId() == contactId) {
                store[argument] = contact;
                store[argument].setId(contactId);
                contact = store[argument];
                System.out.println("This contact was changed");
                break;
            } else {
                if (argument == store.length - 1) {
                    System.out.println("Contact for change not found");
                }
            }
        }
        return contact;
    }

    public void getAllContact() {
        for (Contact storeContact : store) {
            System.out.println(storeContact == null ? "Null" : storeContact.toString());
        }
    }

    public void deleteContactById(int contactId) {
        deleteContactByEntity(getContactById(contactId));
    }

    public void deleteContactByEntity(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].equals(contact)) {
                store[argument] = null;
                System.out.println("This contact was deleted");
                System.out.println(contact.toString());
                break;
            } else {
                if (argument == store.length - 1) {
                    System.out.println("Contact for deleted not found");
                }
            }
        }
    }

    public Contact[] getStore() {
        return store;
    }
}

