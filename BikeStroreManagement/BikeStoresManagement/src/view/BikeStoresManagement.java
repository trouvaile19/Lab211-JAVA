package view;

import controllers.BikeList;
import java.util.Scanner;
import model.IBikeFunction;

public class BikeStoresManagement {

    public static void main(String[] args) {
        IBikeFunction list = new BikeList();
        boolean isReturnMenu = true;
        while (isReturnMenu) {
            list.storagetxt();
            menu();
            try {
                Scanner sc = new Scanner(System.in);
                int choose = Integer.parseInt(sc.nextLine());
                if (choose >= 0 & choose < 7) {
                    switch (choose) {
                        case 1: // create a product
                            do {
                                list.newProduct();
                                contiOrNot();
                            } while (isContinue(sc));
                            break;
                        case 2: // search a product
                            do {
                                list.searchingProduct();
                                contiOrNot();
                            } while (isContinue(sc));
                            break;
                        case 3: // update a information
                            do {
                                list.updateInfo();
                                contiOrNot();
                            } while (isContinue(sc));
                            break;
                        case 4: // delete a product info
                            do {
                                list.deleteProduct();
                                contiOrNot();
                            } while (isContinue(sc));
                            break;
                        case 5: // save to file
                            do{
                                list.saveToFile();
                                contiOrNot();
                            }while (isContinue(sc));
                            break;
                        case 6: // print all lists from file
                            do{
                                list.readProductsFile();
                                contiOrNot();
                            }while(isContinue(sc));
                            break;
                        case 0:
                            System.out.println("Thank you & See you again!");
                            isReturnMenu = false;
                            break;
                    }
                } else {
                    System.out.println("Your choice must be 0 to 6!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Try again! Your number is unvalid!");
            }
        }
    }

    public static void menu() {
        System.out.println("________________________________________");
        System.out.println("|***Welcome to Bike Store Management*** |");
        System.out.println("| 1. Create a new product               |");
        System.out.println("| 2. Search product information by name |");
        System.out.println("| 3. Update product information         |");
        System.out.println("| 4. Delete product information         |");
        System.out.println("| 5. Save to file                       |");
        System.out.println("| 6. Print all lists from file          |");
        System.out.println("| 7. Search product and count           |");
        System.out.println("| 0. Exit the program!                  |");
        System.out.println("|---------------------------------------|");
        System.out.println("     !REMEMBER TO SAVE TO FILE! (5)      ");
        System.out.print("Enter your choice(0->6): ");
    }

    public static boolean isContinue(Scanner sc) {
        while (true) {
            try {
                int choose = Integer.parseInt(sc.nextLine());
                if (choose == 1) {
                    return true;
                } else if (choose == 0) {
                    return false;
                } else {
                    System.out.print("\nYour choice is unvalid! Try again: ");
                    return isContinue(sc);
                }
            } catch (NumberFormatException e) {
                System.out.print("\nYour choice must be '1' or '0'! Try again: ");
            }
        }
    }

    public static void contiOrNot() {
        System.out.println("Do you wanna continue to use this function?");
        System.out.print("Press '1' = Yes; '0' = No (back to Menu): ");
    }
}
