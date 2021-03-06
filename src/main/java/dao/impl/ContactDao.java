package dao.impl;

import dao.IContactDao;
import entity.Contact;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;

import java.util.Objects;

public class ContactDao implements IContactDao {

    private static int generator = 0;

    private Contact[] store = new Contact[2];

    public void saveContact(Contact contact) throws ExceptionsAddressBook {
        for (int argument = 0; argument < store.length; ++argument) {
            if (store[argument] == null) {
                contact.setId(++generator);
                store[argument] = contact;
                System.out.println("This NEW contact was added in your contact book");
                System.out.println(contact.toString());
                break;
            } else {
                if (argument == store.length - 1) {
                    throw new ExceptionsAddressBook(ErrorCode.CONTACT_NOT_SAVED);
                }

            }
        }
    }

    public Contact saveContactById(Contact contact, int contactId) throws ExceptionsAddressBook {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].getId() == contactId) {
                contact.setId(contactId);
                store[argument] = contact;
                System.out.println("Contact number " + contactId + " has been saved to your contact book");
                System.out.println(contact.toString());
                break;
            } else {
                if (argument == store.length - 1) {
                    throw new ExceptionsAddressBook(ErrorCode.CONTACT_NOT_SAVED);
                }
            }
        }
        return contact;
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

    @Override
    public Contact updateContact(Contact contact) {
        for (Contact storeContacts : getStore()) {
            if (Objects.equals(storeContacts.getId(), contact.getId())) {
                storeContacts = contact;
                return storeContacts;
            }
        }
        return contact;
    }

    @Override
    public void saveContactNameById(int contactId, String contactName) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].getId() == contactId) {
                store[argument].setName(contactName);
                System.out.println("This contact Name was changed");
                break;
            } else {
                if (argument == store.length - 1) {
                    System.out.println("Contact for change not found");
                }
            }
        }
    }

    @Override
    public void saveContactSurNameById(int contactId, String contactSurName) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].getId() == contactId) {
                store[argument].setSurName(contactSurName);
                System.out.println("This contact Sur Name was changed");
                break;
            } else {
                if (argument == store.length - 1) {
                    System.out.println("Contact for change not found");
                }
            }
        }
    }

    @Override
    public void saveContactPhoneNumberById(int contactId, String contactPhoneNumber) {
        for (int argument = 0; argument < store.length; argument++) {
            if (store[argument] != null && store[argument].getId() == contactId) {
                store[argument].setPhoneNumber(contactPhoneNumber);
                System.out.println("This contact Phone Number was changed");
                break;
            } else {
                if (argument == store.length - 1) {
                    System.out.println("Contact for change not found");
                }
            }
        }
    }

    public void getAllContact() {
        for (Contact storeContact : store) {
            System.out.println(storeContact == null ? "Null" : storeContact.toString());
        }
    }

    public void deleteContactById(int contactId) {
        deleteContactByEntity(getContactById(contactId));
    }

    private void deleteContactByEntity(Contact contact) {
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

    private Contact[] getStore() {
        return store;
    }
}

