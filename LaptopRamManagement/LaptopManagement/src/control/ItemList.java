package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IRamList;
import model.RAMItem;

public class ItemList extends ArrayList<RAMItem> implements IRamList{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    
    transient Scanner sc = new Scanner(System.in); // không cần tuần tự hóa
    @Override
    public void addItem() {
        String code;
        String production_month_year;
        int quantity;
        boolean isActive;
                
        while (true){
            System.out.println("\nInput code (RAMx_y) <x and y (0->9)>: ");
            code = sc.nextLine();
            if(!code.matches("^RAM\\d+_\\d+$")){
                System.out.println(ANSI_RED + "Incorrect format(RAMx_y)!" + ANSI_RESET);
            }else break;
        }
        
        System.out.println("Input type: ");
        String type = sc.nextLine();
        System.out.println("Input bus: ");
        String bus = sc.nextLine();
        System.out.println("Input brand: ");
        String brand = sc.nextLine();
        
        while (true) {
            try{
            System.out.println("Input quantity: ");
            quantity = Integer.parseInt(sc.nextLine());
            break;
            } catch(NumberFormatException e){
                System.out.println(ANSI_RED + "The quantity must be an number!" + ANSI_RESET);
            }
        }
        
        while (true) {
            System.out.println("Input month & year model(xx/zzzz): ");
            production_month_year = sc.nextLine();
            if (!production_month_year.matches("(0[1-9]|1[0-2])/\\d{4}")) {
                System.out.println(ANSI_RED + "Incorrect format(xx/zzzz)!" + ANSI_RESET);
            } else break;
        }
        System.out.println("Do your want to active this Ram([Y]:yes; [N]: no): ");
        String s = sc.nextLine();
        if(s.equalsIgnoreCase("Y")){
           isActive = true;
            System.out.println(ANSI_GREEN + "Sucessfully active!" + ANSI_RESET );
        }else{
            isActive = false;
            System.out.println(ANSI_RED + "Failed to active!" + ANSI_RESET);
        }        
        RAMItem item = new RAMItem(code, type, bus, brand, quantity, production_month_year, isActive);
        this.add(item);

    }

    @Override
    public void searchType() {
        int count = 0;
        System.out.print("Enter the spectific type you want to search: ");
        String search = sc.nextLine();
        for (RAMItem item : this) {
            if(item.getType().equalsIgnoreCase(search)){
                System.out.println(String.format("%s, %s, %s, %d", item.getCode(), item.getType(),
                        item.getProduction_month_year(), item.getQuantity()) );
                count += 1;
            }
        }
        System.out.println(ANSI_YELLOW + count + " results to be found " + ANSI_RESET);   
    }

    @Override
    public void searchBus() {
        int count = 0;
        System.out.println("Enter the spectific bus you want to search: ");
        String search = sc.nextLine();
        for (RAMItem item : this) {
            if(item.getBus().equalsIgnoreCase(search)){
                System.out.println(String.format("%s, %s, %s, %d", item.getCode(), item.getBus(),
                        item.getProduction_month_year(), item.getQuantity()) );
                count += 1;
            }
        }
        System.out.println(ANSI_YELLOW + count + " results to be found" + ANSI_RESET);
    }

    @Override
    public void searchBrand() {
        int count = 0;
        System.out.println("Enter the spectific brand you want to search: ");
        String search = sc.nextLine();
        for (RAMItem item : this) {
            if(item.getBrand().equalsIgnoreCase(search)){
                System.out.println(String.format("%s, %s, %s, %d", item.getCode(), item.getBrand(),
                        item.getProduction_month_year(), item.getQuantity()) );
                count += 1;
            }
        }
        System.out.println(ANSI_YELLOW + count + " results to be found" + ANSI_RESET);
    }

    @Override
    public void updateItemInfo() {
        System.out.println("Enter specific code to update information: ");
        String search = sc.nextLine();
        int count = 0;
        for (RAMItem item : this) {
            if(item.getCode().equalsIgnoreCase(search)){
                subUpdate(search, item);
                System.out.println(ANSI_RESET + "Successfully updated" + ANSI_GREEN);
                count += 1;
            }
        }
        if (count == 0 ){
            System.out.println(ANSI_RESET + "The code doesn't exist!" + ANSI_GREEN);
        }
    }

    @Override
    public void deleteItem() {
        System.out.println("Are you sure to delete inactive items?");
        System.out.print("Press [Y] to delete / Press [0] to cancel: ");
        String confirm = sc.nextLine();
        if(confirm.equalsIgnoreCase("Y")){
            Iterator <RAMItem> it = this.iterator();
            while(it.hasNext()) {
                RAMItem item = it.next();
                if(!item.isIsActive())    {it.remove();}
            }
            System.out.println(ANSI_GREEN + "Sucessfully deleted inactive items" + ANSI_RESET);
        }else{
            System.out.println(ANSI_GREEN + "Canceled to delete!" + ANSI_RESET);
        }
    }

    @Override
    public void displayAll() {
        int count = 0;
        header();
        for (RAMItem item : this) {
            if(item.isIsActive()){
                System.out.println(item);
                count += 1;
            }
        }
        if(count == 0) System.out.println(ANSI_RED + "                -EMPTY!-" + ANSI_RESET);
    }

    @Override
    public void loadData() {
        String path = "src\\data\\items.dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\data\\items.dat"))) {
            ArrayList<RAMItem> items = (ArrayList<RAMItem>) ois.readObject();
            this.addAll(items);
        }catch(IOException | ClassNotFoundException e){
             System.out.println(ANSI_RED + "Failed to load!" + ANSI_RESET);
        }
    }

    @Override
    public void savetoFile() {
        String path = "src\\data\\items.dat";    
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(this);
            System.out.println(ANSI_GREEN + "Successfully save objects to items.dat" + ANSI_RESET);    
        }catch(IOException e){
            System.out.println(ANSI_RED + "Failed to save!" + e.getMessage() + ANSI_RESET);
        }
    }

    @Override
    public void subUpdate(String code, RAMItem item) {
        String oldType = item.getType();
        String oldBus = item.getBus();
        String oldBrand = item.getBrand();
        int oldQuantity = item.getQuantity();

        System.out.print("Enter new type: ");
        String newType = sc.nextLine();
        if (newType.isEmpty()) {
            item.setType(oldType);
        }else item.setType(newType);
        
        System.out.print("Enter new bus: ");
        String newBus = sc.nextLine();
        if (newBus.isEmpty()) {
            item.setBus(oldBus);
        } else item.setBus(newBus);
        
        System.out.print("Enter new Brand: ");
        String newBrand = sc.nextLine();
        if(newBrand.isEmpty()) {
            item.setBrand(oldBrand);
        }else item.setBrand(newBrand);
        while (true) {
            try{
                System.out.print("Enter new quantity: ");
                int newQuantity = Integer.parseInt(sc.nextLine());
                if (String.valueOf(newQuantity).isEmpty()) {
                    item.setQuantity(oldQuantity);
                    break;
                } if (newQuantity < 0){
                    System.out.println(ANSI_RED +  "The Quantity must be larger than 0 !" + ANSI_RESET);
                } else item.setQuantity(newQuantity); break;
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid number!" + ANSI_RESET);     
            }
        }
    }

    @Override
    public void readTypeFile() {
        String paths = "src\\data\\RamTypes.txt";
        File file = new File(paths);
        try {
            FileReader fr = new FileReader(file);
            String line;
            List<String> typeList = new ArrayList<>();
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null){
                typeList.add(line);
            }
            String[] types = new String[typeList.size()];
            for(int i = 0; i < typeList.size(); i++){
                String[] temp = typeList.get(i).trim().split(", ");
                types[i] = temp[i]; 
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println(ANSI_RED + "Failed to read!" + ANSI_RESET);
        } catch (IOException ex) {
            System.out.println(ANSI_RED + "Failed to read!" + ANSI_RESET);
        }
        
    }

    @Override
    public void readSpeedFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void header() {
        System.out.println( ANSI_PURPLE + "| Code | Type | Bus  |Brand |Quantity|  MFG  |" + ANSI_RESET); 
    }
    //       ("|%6s|%6s|%6s|%6s|%4d|%10s|", code, type, bus, brand, quantity, production_month_year);

}
