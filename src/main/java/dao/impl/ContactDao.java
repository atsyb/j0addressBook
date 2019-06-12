package dao.impl;

import dao.IContactDao;
import entity.Contact;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static dao.QueryName.*;
import static dao.QuerySQL.getQuery;

public class ContactDao implements IContactDao {

    private ArrayList<Contact> store = new ArrayList<>();

    public void saveContact(Contact contact) throws ExceptionsAddressBook {
        if (!Objects.isNull(contact)) {
            store.add(contact);
        } else {
            throw new ExceptionsAddressBook(ErrorCode.CONTACT_NOT_SAVED);
        }
    }

    public Contact getContactByName(String contactName) {
        Optional<Contact> optionalContact = store.stream().filter(n -> n.getName().equals(contactName)).findFirst();
        return optionalContact.orElse(null);
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

    public ArrayList<Contact> getStore() {
        return store;
    }
}
