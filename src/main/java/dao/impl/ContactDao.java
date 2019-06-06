package dao.impl;

import dao.IContactDao;
import entity.Contact;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;

import java.sql.*;
import java.time.LocalDate;

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
    public Contact insertContact(Contact contact, Connection conn) throws ExceptionsAddressBook {
        try (PreparedStatement stmt = conn.prepareStatement(getQuery(INSERT_PERSON))) {
            int indexStatement = 0;
            stmt.setInt(++indexStatement, contact.getId());
            stmt.setString(++indexStatement, contact.getName());
            stmt.setString(++indexStatement, contact.getSurName());
            stmt.setString(++indexStatement, contact.getPhoneNumber());
            stmt.setInt(++indexStatement, contact.getAge());
            stmt.setDouble(++indexStatement, contact.getHeight());
            stmt.setBoolean(++indexStatement, contact.isMarried());
            stmt.setDate(++indexStatement, Date.valueOf(LocalDate.now()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionsAddressBook(ErrorCode.SQL_ERROR_INSERT_CONTACT);
        }
        return contact;
    }

    @Override
    public Contact selectContactById(int contactId, Connection conn) throws ExceptionsAddressBook {
        Contact contact = new Contact();
        try (PreparedStatement stmt = conn.prepareStatement(getQuery(SELECT_PERSON_BY_ID))) {
            stmt.setInt(1, contactId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int indexStatement = 0;
                    contact.setId(rs.getInt(++indexStatement));
                    contact.setName(rs.getString(++indexStatement));
                    contact.setSurName(rs.getString(++indexStatement));
                    contact.setPhoneNumber(rs.getString(++indexStatement));
                    contact.setAge(rs.getInt(++indexStatement));
                    contact.setHeight(rs.getDouble(++indexStatement));
                    contact.setMarried(rs.getBoolean(++indexStatement));
                    contact.setDateOfCreation(rs.getDate(++indexStatement).toLocalDate());
                } else {
                    contact = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionsAddressBook(ErrorCode.SQL_ERROR_SELECT_CONTACT);
        }
        return contact;
    }

    @Override
    public Contact updateContactById(Contact contact, int contactId, Connection conn) throws ExceptionsAddressBook {
        try (PreparedStatement stmt = conn.prepareStatement(getQuery(UPDATE_PERSON_BY_ID))) {
            int indexStatement = 0;
            stmt.setString(++indexStatement, contact.getName());
            stmt.setString(++indexStatement, contact.getSurName());
            stmt.setString(++indexStatement, contact.getPhoneNumber());
            stmt.setInt(++indexStatement, contact.getAge());
            stmt.setDouble(++indexStatement, contact.getHeight());
            stmt.setBoolean(++indexStatement, contact.isMarried());
            stmt.setDate(++indexStatement, Date.valueOf(LocalDate.now()));
            stmt.setInt(++indexStatement, contact.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionsAddressBook(ErrorCode.SQL_ERROR_UPDATE_CONTACT);
        }
        return contact;
    }

    @Override
    public Contact deleteContactById(int contactId, Connection conn) throws ExceptionsAddressBook {
        Contact contact = selectContactById(contactId, conn);
        try (PreparedStatement stmt = conn.prepareStatement(getQuery(DELETE_PERSON_BY_ID))) {
            int indexStatement = 0;
            stmt.setInt(++indexStatement, contactId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionsAddressBook(ErrorCode.SQL_ERROR_DELETE_CONTACT);
        }
        return contact;
    }

    public void getAllContact() {
        for (Contact storeContact : store) {
            System.out.println(storeContact == null ? "Null" : storeContact.toString());
        }
    }

    public Contact[] getStore() {
        return store;
    }
}

