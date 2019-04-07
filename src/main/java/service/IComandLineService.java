package service;

import dao.impl.ContactDao;
import service.impl.ContactService;

import java.util.Scanner;

public interface IComandLineService {

    Scanner scanner = new Scanner(System.in);

    static final ContactService service = new ContactService(new ContactDao());

    public static void showMenu() {
        System.out.println("1.Add contact;");
        System.out.println("2.Update contact by Id");
        System.out.println("3.Delete contact;");
        System.out.println("4.Show all contacts");
        System.out.println("5.Show contact by Id");
        System.out.println("6.Show contact by Name");
        System.out.println("0.Exit.");
    }
    public static void run() {
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
                    service.editContactById(scanner);
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
                    service.showContactById(scanner);
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
}
