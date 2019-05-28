package service;

import dao.impl.ContactDao;
import exceptions.ErrorCode;
import exceptions.ExceptionsAddressBook;
import service.impl.ContactService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @version 1.0
 */
public interface IComandLineService {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    ContactService service = new ContactService(new ContactDao());

    /**
     * Drawing the main menu
     */
    static void showMenu() {
        System.out.println("1.Add contact;");
        System.out.println("2.Update contact");
        System.out.println("3.Delete contact;");
        System.out.println("4.Show all contacts");
        System.out.println("5.Show contact by Id");
        System.out.println("6.Show contact by Name");
        System.out.println("0.Exit.");
    }

    /**
     * Running a selection on the main menu
     */
    static void run() {
        service.recoveryContact("backup","contact");

        boolean exit = true;
        do {
            try {
                System.out.println("  *** Chose your wish:");
                showMenu();
                String stringOfMenu = reader.readLine();

                if (!stringOfMenu.matches("\\d+")) {
                    System.out.println("You need to select numbers from the menu!");
                    throw new ExceptionsAddressBook(ErrorCode.ENTERED_NOT_INTEGER);
                }

                int numberOfMenu = Integer.valueOf(stringOfMenu);
                switch (numberOfMenu) {
                    case 1: {
                        service.addContact(reader);
                        break;
                    }
                    case 2: {
                        service.alterContact(reader);
                        break;
                    }
                    case 3: {
                        //service.delContactById(scanner);
                        service.delContactById(reader);
                        break;
                    }
                    case 4: {
                        service.showAllContact();
                        break;
                    }
                    case 5: {
                        service.getContact(reader);
                        break;
                    }
                    case 6: {
                        service.showContactByName(reader);
                        break;
                    }
                    case 0: {
                        service.backupContact("backup","contact");
                        System.out.println("Thank you that use our app. Good bye.");
                        exit = false;
                        break;
                    }
                    default: {
                        System.out.println("Sorry. You enter wrong number of menu.Chose another number.");
                        throw new ExceptionsAddressBook(ErrorCode.ENTERED_INTEGER_OUTOFRANGE);
                    }
                }

            } catch (ExceptionsAddressBook e) {
                System.out.println(e.getErrorCode().getMessageWithCode());
                //continue;
            } catch (NumberFormatException nfe) {
                System.out.println("---NumberFormatException: " + nfe.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (exit);
    }


    /**
     * Drawing a contact editing menu
     */
    static void showMenuEditContact() {
        System.out.println("Select a field to edit:");
        System.out.println("  1.Edit contact name");
        System.out.println("  2.Edit contact sur name");
        System.out.println("  3.Edit contact phone number");
        //System.out.println("0.Return to the main menu");
    }


}
