package service;

import dao.impl.ContactDao;
import service.impl.ContactService;

import java.util.Scanner;

/**
 * @version 1.0
 */
public interface IComandLineService {

    Scanner scanner = new Scanner(System.in);

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
        boolean exit = true;
        do {
            System.out.println("  *** Chose your wish:");
            showMenu();
            int numberOfMenu = scanner.nextInt();
            switch (numberOfMenu) {
                case 1: {
                    service.addContact(scanner);
                    break;
                }
                case 2: {
                    service.alterContact(scanner);
                    break;
                }
                case 3: {
                    service.delContactById(scanner);
                    break;
                }
                case 4: {
                    service.showAllContact();
                    break;
                }
                case 5: {
                    service.getContact(scanner);
                    break;
                }
                case 6: {
                    service.showContactByName(scanner);
                    break;
                }
                case 0: {
                    System.out.println("Thank you that use our app. Good bye.");
                    exit = false;
                    break;
                }
                default: {
                    System.out.println("Sorry. You enter wrong number of menu.Chose another number.");
                }
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
