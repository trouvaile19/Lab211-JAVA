package view;

import java.util.Scanner;

public class MenuView {
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    
    void mainMenu(){
        System.out.println("\n________________________________________");
        System.out.println(ANSI_CYAN_BACKGROUND + ANSI_RED + "|***Welcome to Laptop Ram Management*** |" + ANSI_RESET);
        System.out.println("| 1. Create a new Item                  |");
        System.out.println("| 2. Search Items                       |");
        System.out.println("| 3. Update Item information            |");
        System.out.println("| 4. Delete inactive Items              |");
        System.out.println("| 5. Show all active Items              |");
        System.out.println("| 6. Store data to file                 |");
        System.out.println("| 7. New function                       |");
        System.out.println("| 0. Exit the program!                  |");
        System.out.println("|---------------------------------------|");
        System.out.println(ANSI_RED + "     !REMEMBER TO SAVE TO FILE! (6)      " + ANSI_RESET);
        System.out.print("Enter your choice(0->7): ");
    }
    
    void subSearchMenu(){
        System.out.println("________________________________");
        System.out.println("| 1. Search Items by TYPE       |");
        System.out.println("| 2. Search Items by BUS        |");
        System.out.println("| 3. Search ITems by BRAND      |");
        System.out.println("| 0. Exit                       |");
        System.out.println(" -------------------------------");
        System.out.print("Your option (0->3): ");
    }
    
    void subDisplayMenu(){
        System.out.println("______________________________");
        System.out.println("| 1. Sort Items by TYPE       |");
        System.out.println("| 2. Sort Items by BUS        |");
        System.out.println("| 3. Sort ITems by BRAND      |");
        System.out.println("| 0. Exit                     |");
        System.out.println(" -----------------------------");
        System.out.print("Your option (1->3): ");
    }
    
    void availableData(){
        System.out.println(ANSI_YELLOW + "RAM Types: LPDDR5, DDR5, LPDDR4, DDR4, GDDR6" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "BusSpeeds: 5600, 4800, 3200, 5000, 9800, 7200, 6400, 3600" + ANSI_RESET );
    }
    
    boolean isContinue(Scanner sc) {
        while (true) {
            try {
                int choose = Integer.parseInt(sc.nextLine());
                if (choose == 1) {
                    return true;
                } else if (choose == 0) {
                    return false;
                } else {
                    System.out.print(ANSI_RED + "\nYour choice is unvalid! Try again: " + ANSI_RESET );
                    return isContinue(sc);
                }
            } catch (NumberFormatException e) {
                System.out.print(ANSI_RED + "\nYour choice must be '1' or '0'! Try again: " + ANSI_RESET);
            }
        }
    }

    void contiOrNot() {
        System.out.println("Do you wanna continue to use this function?");
        System.out.print(ANSI_BLUE + "Press '1' = Yes; '0' = No (back to Menu): " + ANSI_RESET);
    }
}
