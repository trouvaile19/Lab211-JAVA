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
        System.out.println("| 1. Manage the Topics                  |");
        System.out.println("| 2. Manage the Course                  |");
        System.out.println("| 3. Manage the Learner                 |");
        System.out.println("| 4. Search Information                 |");
        System.out.println("| 5. SAVE Topic, Course, Learner to file|");
        System.out.println("| 6. New Function                       |");
        System.out.println("| 0. EXIT the program!                  |");
        System.out.println("|---------------------------------------|");
        System.out.println(ANSI_RED + "     !REMEMBER TO SAVE TO FILE! (6)      " + ANSI_RESET);
        System.out.print("Enter your choice(0->7): ");
    }
    
    void subTopicsMenu(){
        System.out.println("________________________________");
        System.out.println("| 1. Add Topics to catalog      |");
        System.out.println("| 2. Update Topic               |");
        System.out.println("| 3. Delete Topic               |");
        System.out.println("| 4. Display all Topics         |");
        System.out.println(" -------------------------------");
        System.out.print("Your option (1->4): ");
    }
    
    void subCourseMenu(){
        System.out.println("_______________________________");
        System.out.println("| 1. Add Course                |");
        System.out.println("| 2. Update Course             |");
        System.out.println("| 3. Delete Course             |");
        System.out.println("| 4. Display Course information|");
        System.out.println(" ------------------------------");
        System.out.print("Your option (1->4): ");
    }
    
    void subLearnerMenu(){
        System.out.println("________________________________");
        System.out.println("| 1. Add Learner to Course      |");
        System.out.println("| 2. Enter scores for learners  |");
        System.out.println("| 3. Display Learner information|");
        System.out.println(" -------------------------------");
        System.out.print("Your option (1->3): ");
    }
    
    void subSearchMenu(){
        System.out.println("________________________________");
        System.out.println("| 1. Search Topic               |");
        System.out.println("| 2. Search Course              |");
        System.out.println(" -------------------------------");
        System.out.print("Your option (1->2): ");
    }
    
    void subSearchCourseMenu(){
        System.out.println("________________________________");
        System.out.println("| 1. Search Course by Topic     |");
        System.out.println("| 2. Search Course by Name      |");
        System.out.println(" -------------------------------");
        System.out.print("Your option (1->2): ");
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

