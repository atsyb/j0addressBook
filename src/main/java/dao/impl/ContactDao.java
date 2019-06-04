package dao.impl;

import dao.IContactDao;
import dao.MySQLdb;
import entity.Contact;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;
import service.impl.ContactService;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

import static dao.QueryName.*;
import static dao.QuerySQL.getQuery;


public class ContactDao implements IContactDao {

    private static int generator = 0;

    private Contact[] store = new Contact[10];

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
    public Contact insertContact(Contact contact) {
        try (Connection conn = MySQLdb.getConnectionMyDB(); PreparedStatement stmt = conn.prepareStatement(getQuery(INSERT_PERSON))) {
            int i = 0;
            stmt.setInt(++i, contact.getId());
            stmt.setString(++i, contact.getName());
            stmt.setString(++i, contact.getSurName());
            stmt.setString(++i, contact.getPhoneNumber());
            stmt.setInt(++i, contact.getAge());
            stmt.setDouble(++i, contact.getHeight());
            stmt.setBoolean(++i, contact.isMarried());
            stmt.setDate(++i, Date.valueOf(LocalDate.now()));
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("ERROR insertContact!");
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public Contact selectContactById(int contactId) {
        Contact contact = new Contact();
        try (Connection conn = MySQLdb.getConnectionMyDB(); PreparedStatement stmt = conn.prepareStatement(getQuery(SELECT_PERSON_BY_ID))) {
            stmt.setInt(1, contactId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int i = 0;
                    contact.setId(rs.getInt(++i));
                    contact.setName(rs.getString(++i));
                    contact.setSurName(rs.getString(++i));
                    contact.setPhoneNumber(rs.getString(++i));
                    contact.setAge(rs.getInt(++i));
                    contact.setHeight(rs.getDouble(++i));
                    contact.setMarried(rs.getBoolean(++i));
                    contact.setDateOfCreation(rs.getDate(++i).toLocalDate());
                } else {
                    contact = null;
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR selectContactByID!");
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public Contact updateContactById(Contact contact, int contactId) {
        try (Connection conn = MySQLdb.getConnectionMyDB(); PreparedStatement stmt = conn.prepareStatement(getQuery(UPDATE_PERSON_BY_ID))) {
            int i = 0;
            stmt.setString(++i, contact.getName());
            stmt.setString(++i, contact.getSurName());
            stmt.setString(++i, contact.getPhoneNumber());
            stmt.setInt(++i, contact.getAge());
            stmt.setDouble(++i, contact.getHeight());
            stmt.setBoolean(++i, contact.isMarried());
            stmt.setDate(++i, Date.valueOf(LocalDate.now()));
            stmt.setInt(++i, contact.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("ERROR updateContactById!");
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public Contact deleteContactById(int contactId) {
        Contact contact = selectContactById(contactId);
        try (Connection conn = MySQLdb.getConnectionMyDB(); PreparedStatement stmt = conn.prepareStatement(getQuery(DELETE_PERSON_BY_ID))) {
            int i = 0;
            stmt.setInt(++i, contactId);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("ERROR deleteContactById!");
            e.printStackTrace();
        }
        return contact;
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

    public void deleteContactByIdArr(int contactId) {
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

    public Contact[] getStore() {
        return store;
    }
}

