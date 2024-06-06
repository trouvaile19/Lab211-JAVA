
package models;

import controllers.VehicleList;
import java.util.Scanner;

/**
 *
 * @trouvaile19
 */
public interface IVehicleList {

    void addVehicle();
    
    void checkExist();
    
    void update();
    
    void delete();
    
    void searchName();
    
    void searchId();
    
    void displayList();
    
    void displayDescendingPrice();
    
    void saveToFile();
    
    void printAll();
    
    void printDescendingPrice();       

    VehicleList readFile();
}
