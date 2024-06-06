/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclemanagement;

import controllers.VehicleList;
import java.util.Scanner;
import models.IVehicleList;

public class WorkWithFile {

    // @trouvaile19
    public static void main(String[] args) {

        IVehicleList list = new VehicleList();
        boolean loopMenu = true;
        while (loopMenu) {
            menu();
            try {
                Scanner sc = new Scanner(System.in);
                int choose = sc.nextInt();
                int con;
                switch (choose) {
                    case 0:
                        do {
                            list.addVehicle();//add new vehicle
                            //You want to continue or not?
                            contiOrNot();
                            con = sc.nextInt();
                        } while (continueOrNot(con, sc));
                        break;
                    case 1:
                        do {
                            list.checkExist();//check id if id exist or not
                            //You want to continue or not?
                            contiOrNot();
                            con = sc.nextInt();
                        } while (continueOrNot(con, sc));
                        break;
                    case 2: //update new information for object
                        list.update();
                        break;
                    case 3:
                        list.delete();
                        break;
                    case 4://Searching vehicle in the list
                        subMenuSearch(); 
                        double choose4 = sc.nextDouble();
                        if (choose4 == 4.1) { // Search by name_vehicle
                            list.searchName();
                        }
                        if (choose4 == 4.2) { //Search by Id_vehicle
                            list.searchId();
                        } else {
                            System.out.println("Invaid choose!");
                        }
                        break;
                    case 5:// Display vehicle list from ArrayList<>
                        subMenuDisplay();
                        double choose5 = sc.nextDouble();
                        if(choose5 == 5.1){
                        list.displayList(); //show all vehicles in ArrayList
                        }
                        if(choose5 == 5.2){ // show all vehicles by descending prrices
                        list.displayDescendingPrice();
                        }
                        break;
                    case 6://save file
                        list.saveToFile();
                        break;
                    case 7://print vehicle list from the file
                        subMenuprint();
                        double choose7 = sc.nextDouble();
                        if(choose7 == 7.1){ //print all
                            list.printAll();
                        }
                        if(choose7 == 7.2){ //print descending file
                            list.printDescendingPrice();
                        }
                        break;
                    case 8://exit
                        System.out.println("Thank you for your using app!");
                        loopMenu = false;
                        break;
                    default:
                        System.out.println("Unsatisfied number! TRY ANGAIN!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid Choice! Please Try Again!");
            }
        }

    }

    public static void menu() {
        System.out.println("\n|***Welcome to my Vehicle Management*** |");
        System.out.println("| 0. Adding new vehicle                 |");
        System.out.println("| 1. Checkign exits vehicle             |");
        System.out.println("| 2. Updating vehicle                   |");
        System.out.println("| 3. Deleting vehicle                   |");
        System.out.println("| 4. Searching vehicle                  |");
        System.out.println("| 5. Displaying vehicle list            |");
        System.out.println("| 6. Saving Vehicle to file             |");
        System.out.println("| 7. Printing list Vehicles the file.   |");
        System.out.println("| 8. Exit the program!                  |");
        System.out.println("|---------------------------------------|");
        System.out.print("Enter your choice(0->8): ");
    }

    public static void subMenuSearch() {
        System.out.println("\n|----------SubMenu-----------|");
        System.out.println("| 4.1 Search by name_vehicle |");
        System.out.println("| 4.2 Search by Id_vehicle   |");
        System.out.println("|----------------------------|");
        System.out.println("Your choice: ");
    }

    public static void subMenuDisplay() {
        System.out.println("\n|-----------------SubMenu-------------------|");
        System.out.println("| 5.1 Show all vehicle list                 |");
        System.out.println("| 5.2 Show all (descending by vehicle price)|");
        System.out.println("|-------------------------------------------|");
        System.out.println("Your choice: ");
    }
    
     public static void subMenuprint() {
        System.out.println("\n|-----------------SubMenu-------------------|");
        System.out.println("| 7.1 Show all vehicle list                 |");
        System.out.println("| 7.2 Show all (descending by vehicle price)|");
        System.out.println("|-------------------------------------------|");
        System.out.println("Your choice: ");
    }
    
    public static void contiOrNot() {
        System.out.println("Do you wanna continue to add new vehicle?");
        System.out.print("Press '1' = Yes; '0' = No (back to Menu): ");
    }

    public static boolean continueOrNot(int con, Scanner sc) {
        while (true) {
            if (con == 1) {
                return true;
            }
            if (con == 0) {
                return false;
            }
        }
    }
}
