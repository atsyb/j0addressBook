package service.impl;

import dao.impl.ContactDao;
import entity.Contact;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;
import service.IContactService;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

import static service.IComandLineService.showMenuEditContact;

public class ContactService implements IContactService {

    private ContactDao contactDao;


    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Contact createContact(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        Contact contact = new Contact();

        System.out.println("Enter please name of your contact person:");
        String name = reader.readLine();
        contact.setName(name);

        System.out.println("Enter please phone number of your contact person");
        String phoneNumber = reader.readLine().replaceAll("[^0-9+]", "");
        contact.setPhoneNumber(phoneNumber);

        System.out.println("Want to add fields? [yes(Y)/no(<Enter>)]");
        String wantAddFields = reader.readLine().toLowerCase();
        if (wantAddFields.equals("y")) {

            System.out.println("Enter please sur name of your contact person:");
            String surName = reader.readLine();
            contact.setSurName(surName);

            System.out.println("Enter please age of your contact person:");
            int age = Integer.valueOf(reader.readLine(), 10);
            contact.setAge(age);

            System.out.println("Enter please height of your contact person:");
            double height = Double.parseDouble(reader.readLine());
            contact.setHeight(height); /* "-?\\d+(\\.\\d+)?" */

            System.out.println("Сontact person married? [y/n]");
            String isMarried = reader.readLine().toLowerCase();
            boolean married;
            if (isMarried.equals("y")) {
                married = true;
                contact.setMarried(married);
            } else if (isMarried.equals("n")) {
                married = false;
                contact.setMarried(married);
            } else {
                System.out.println("  Married unknown! Set false.");
                throw new ExceptionsAddressBook(ErrorCode.ENTERED_INTEGER_MARRIED);
            }
        }
        contact.setDateOfCreation(LocalDate.now());

        return contact;
    }

    private Contact loadContact(String name, String surName, String phoneNumber, int age, double height, boolean married) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhoneNumber(phoneNumber);
        contact.setSurName(surName);
        contact.setAge(age);
        contact.setHeight(height);
        contact.setMarried(married);
        contact.setDateOfCreation(LocalDate.now());
        return contact;
    }

    @Override
    public Contact addContact(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        Contact contact = createContact(reader);
        contactDao.saveContact(contact);
        System.out.println("Thank you for saving your new contact in this contact book.");
        return contact;
    }

    @Override
    public Contact getContact(BufferedReader reader) throws IOException {
        System.out.println("Enter please ID of your contact person:");
        int id = Integer.valueOf(reader.readLine());
        return contactDao.getContactById(id);
    }

    @Override
    public void showContactByName(BufferedReader reader) throws IOException {
        System.out.println("Enter please name of your contact person:");
        String name = reader.readLine();
        contactDao.getContactByName(name);
    }

    @Override
    public Contact alterContact(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        Contact contact = getContact(reader);
        if (Objects.nonNull(contact)) {
            int id = contact.getId();
            System.out.println("ALTER: " + contact.toString());
            contact = modifierFields(reader, contact);
            return contactDao.saveContactById(contact, id);
        } else {
            System.out.println("Contact for alter not found!");
            return null;
        }
    }

    private Contact modifierFields(BufferedReader reader, Contact contact) throws ExceptionsAddressBook, IOException {
        showMenuEditContact();
        String fieldNumberStr = reader.readLine();
        if (!fieldNumberStr.matches("\\d+")) {
            throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
        }
        int fieldNumber = Integer.valueOf(fieldNumberStr);
        switch (fieldNumber) {
            case FIELD_NAME: {
                System.out.println("     Enter new name:");
                return editContactField(FIELD_NAME, contact, reader);
            }
            case FIELD_SURNAME: {
                System.out.println("     Enter new sur name:");
                return editContactField(FIELD_SURNAME, contact, reader);
            }
            case FIELD_PHONENUMBER: {
                System.out.println("     Enter new phone number:");
                return editContactField(FIELD_PHONENUMBER, contact, reader);
            }
            case FIELD_AGE: {
                System.out.println("     Enter new age:");
                return editContactField(FIELD_AGE, contact, reader);
            }
            case FIELD_HEIGHT: {
                System.out.println("     Enter new height:");
                return editContactField(FIELD_HEIGHT, contact, reader);
            }
            case FIELD_MARRIED: {
                System.out.println("     Enter new married:");
                return editContactField(FIELD_MARRIED, contact, reader);
            }
            default: {
                System.out.println("Sorry, nothing to change");
                return contact;
            }
        }
    }

    private Contact editContactField(int fieldId, Contact contact, BufferedReader reader) throws IOException {
        String fieldValue = reader.readLine();
        switch (fieldId) {
            case FIELD_NAME: {
                contact.setName(fieldValue);
                break;
            }
            case FIELD_SURNAME: {
                contact.setSurName(fieldValue);
                break;
            }
            case FIELD_PHONENUMBER: {
                contact.setPhoneNumber(fieldValue);
                break;
            }
        }
        return contact;
    }

    @Override
    public void showAllContact() {
        contactDao.getAllContact();
    }

    @Override
    public void delContactById(BufferedReader reader) throws ExceptionsAddressBook, IOException {
        System.out.println("Enter please ID of your contact person for DEL:");
        try {
            int id = Integer.valueOf(reader.readLine());
            contactDao.deleteContactById(id);
        } catch (NumberFormatException nfe) {
            throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
        }
    }

    private void downloadFromFile(String fileName) {
        String[] line;
        File fileToSave = new File(fileName);
        if (fileToSave.exists()) {
            try (BufferedReader bReader = new BufferedReader(new FileReader(fileToSave))) {
                System.out.println(">>>Reading from file: " + fileToSave.getAbsolutePath());
                while (bReader.read() != -1) {
                    line = bReader.readLine().split(";");
                    Contact contact = loadContact(
                            line[FIELD_NAME],
                            line[FIELD_SURNAME],
                            line[FIELD_PHONENUMBER],
                            Integer.valueOf(line[FIELD_AGE]),
                            Double.valueOf(line[FIELD_HEIGHT]),
                            Boolean.valueOf(line[FIELD_MARRIED])
                    );
                    contactDao.saveContact(contact);
                }
            } catch (ExceptionsAddressBook e) {
                System.out.println(e.getErrorCode().getMessageWithCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("[ createNewFile " + fileName + " ]");
            try {
                boolean isFileCreated = fileToSave.createNewFile();
                if (!isFileCreated){
                    System.out.println("Something went wrong. The file was not created!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void unloadToFile(String fileName) {
        File fileToSave = new File(fileName);
        try (BufferedWriter bWrite = new BufferedWriter(new FileWriter(fileToSave))) {
            System.out.println(">>>Write in to " + fileName);
            for (Contact storeContacts : contactDao.getStore()) {
                if (!Objects.isNull(storeContacts)) {
                    bWrite.write(storeContacts.getId() + ";");
                    bWrite.write(storeContacts.getName() + ";");
                    bWrite.write(storeContacts.getSurName() + ";");
                    bWrite.write(storeContacts.getPhoneNumber() + ";");
                    bWrite.write(storeContacts.getAge() + ";");
                    bWrite.write(storeContacts.getHeight() + ";");
                    bWrite.write(storeContacts.isMarried() + ";");
                    bWrite.write(storeContacts.getDateOfCreation().toString());
                    bWrite.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void backupContact(String dirName, String fileName) {
        File backupDir = new File(dirName);
        boolean isDirectoryCreated = backupDir.mkdirs();
        if (isDirectoryCreated) {
            String fileFullName = backupDir + "/" + fileName + System.currentTimeMillis() + ".csv";
            unloadToFile(fileFullName);
        }
    }

    public void recoveryContact(String dirName, String fileName) {
        File backupDir = new File(dirName);

        if (backupDir.isDirectory()) {
            List<File> listFile = Arrays.asList(Objects.requireNonNull(backupDir.listFiles()));
            listFile.forEach(System.out::println);

            Path backupFolder = Paths.get(String.valueOf(backupDir));
            Optional<File> mostRecentFile =
                    Arrays
                            .stream(Objects.requireNonNull(backupFolder.toFile().listFiles()))
                            .filter(File::isFile)
                            .filter(f -> f.getName().contains(fileName))
                            .max(Comparator.comparingLong(File::lastModified));
            if (mostRecentFile.isPresent()) {
                downloadFromFile(mostRecentFile.get().getAbsolutePath());
            } else {
                System.out.println("Recovery file not found!");
            }

        } else {
            System.out.println("Contacts not restored from: " + backupDir.getAbsolutePath());
        }


    }

}
