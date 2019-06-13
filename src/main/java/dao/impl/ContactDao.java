package dao.impl;

import dao.IContactDao;
import entity.Contact;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;

import java.sql.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;



public class ContactDao implements IContactDao {

    private ArrayList<Contact> store = new ArrayList<>();
    private int generator = 0;

    public Contact saveContact(Contact contact) throws ExceptionsAddressBook {
        if (!Objects.isNull(contact)) {
            generator++;
            contact.setId(generator);
            store.add(contact);
        } else {
            throw new ExceptionsAddressBook(ErrorCode.CONTACT_NOT_SAVED);
        }
        return contact;
    }


    public Contact getContactByName(String contactName) {
        Optional<Contact> optionalContact = store.stream().filter(n -> n.getName().equals(contactName)).findFirst();
        return optionalContact.orElse(null);
    }

    @Override
    public Contact getContactById(int contactId) {
        Optional<Contact> optionalContact = store.stream().filter(i -> i.getId() == contactId).findFirst();
        return optionalContact.orElse(null);
    }


    @Override
    public Contact deleteContactById(int contactId) {
        Contact contact = getContactById(contactId);
        if (!store.isEmpty()) {
            store.remove(contact);
        } else {
            System.out.println("Contact list is empty");

        }

        return contact;
    }

    public void getAllContact() {
        if (!store.isEmpty()) {
            store.forEach(System.out::println);
        } else {
            System.out.println("Contact list is empty");
        }
    }

    public ArrayList<Contact> getStore() {
        return store;
    }
}

