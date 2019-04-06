package dao.imp;

import entity.imp.Contact;

public class ContactDao {

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

    public void showContactById(int contactId) {
        if (store[contactId] != null) {
            System.out.println(store[contactId].toString());
        } else {
            System.out.println("There is no contact with ID=" + contactId);
        }
    }

    public void showAllContact(Contact contact) {
        for (Contact storeContact : store) {
            System.out.println(contact.toString());
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

    public void alertContact(Contact contact) {
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

    public void deleteContact(Contact contact) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].equals(contact)) {
                store[argument] = null;
                System.out.println("Contact deleted");
                break;
            } else {
                System.out.println("Nothing to delete");
            }
        }
    }

    public Contact[] getStore() {
        return store;
    }
}

