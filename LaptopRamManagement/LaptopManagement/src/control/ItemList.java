package control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
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
        boolean isActive = true;
        String bus = null;
        String type = null;
                
        while (true){
            boolean validation = true;
            System.out.println("\nInput code (RAMx_y) <x and y (0->9)>: ");
            code = sc.nextLine();
            if(!code.matches("^RAM\\d+_\\d+$")){
                System.out.println(ANSI_RED + "Incorrect format(RAMx_y)!" + ANSI_RESET);
                validation = false;
            }
            for (RAMItem it : this) {
                if(it.getCode().equals(code)){
                    System.out.println(ANSI_RED + "This code has already existed!" + ANSI_RESET);
                    validation = false;
                    break;
                }
            } 
            if(validation) break;
        }
        
        boolean check1 = true;
        while(check1) {
            System.out.println("Input type: ");
            type = sc.nextLine();
            String[] typeList = readTypeFile();
            for (String s : typeList) {
                if (s.equals(type)) {
                    check1 = false;
                    break;
                }
            }
            if(check1){System.out.println(ANSI_RED + "The type doesn't exist!" + ANSI_RESET);}

        } 
        
        boolean check2 = true; 
        while(check2){
            System.out.println("Input bus: ");
            bus = sc.nextLine();
            String[] busList = readSpeedFile();
            for (String s : busList) {
                if(s.equals(bus)) {
                    check2 = false;
                    break;
                }            
            }
            if(check2) {System.out.println(ANSI_RED + "The type doesn't exist!" + ANSI_RESET);}

        }
        
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
        
        RAMItem item = new RAMItem(code, type, bus, brand, quantity, production_month_year, isActive);
        this.add(item);

    }

    @Override
    public void searchType() {
        int count = 0;
        System.out.print("Enter the spectific type you want to search: ");
        String search = sc.nextLine();
        System.out.println(ANSI_PURPLE + "| Code | Type |  MFG  |Quantity|" + ANSI_RESET);

        for (RAMItem item : this) {
            if(item.getType().equalsIgnoreCase(search)){
                System.out.println(String.format("|%6s|%6s|%7s|%8d|", item.getCode(), item.getType(),
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
        System.out.println(ANSI_PURPLE + "| Code | Bus  |  MFG  |Quantity|" + ANSI_RESET);

        for (RAMItem item : this) {
            if(item.getBus().equalsIgnoreCase(search)){
                System.out.println(String.format("|%6s|%6s|%7s|%8d|", item.getCode(), item.getBus(),
                        item.getProduction_month_year(), item.getQuantity()) );
                count += 1;
            }
        }
        System.out.println(ANSI_YELLOW + count + " results were found" + ANSI_RESET);
    }

    @Override
    public void searchBrand() {
        int count = 0;
        System.out.println("Enter the spectific brand you want to search: ");
        String search = sc.nextLine();
        System.out.println(ANSI_PURPLE + "| Code |  Brand |  MFG  |Quantity|" + ANSI_RESET);

        for (RAMItem item : this) {
            if(item.getBrand().equalsIgnoreCase(search)){
                System.out.println(String.format("|%6s|%8s|%7s|%8s|", item.getCode(), item.getBrand(),
                        item.getProduction_month_year(), item.getQuantity()) );
                count += 1; //"|%6s|%6s|%6s|%8s|%8d|%7s|
            } //        System.out.println( ANSI_PURPLE + "| Code | Type | Bus  |  Brand |Quantity|  MFG  |" + ANSI_RESET); 

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
        System.out.println("Enter code Ram to delete: ");
        String search = sc.nextLine();
        for (RAMItem it : this) {
            if(it.getCode().equals(search)){
                it.setIsActive(false);
            }
        }
        System.out.println(ANSI_RESET + "Sucessfully deleted and set this item inactive!" + ANSI_GREEN);
    }

    @Override
    public void displayAll() { //show all items
        int count = 0;
        header();
        for (RAMItem item : this) {
            if(item.isIsActive()){
                System.out.println(item);
                count += 1;
            }
        }
        if(count == 0) System.out.println(ANSI_RED + "                -EMPTY!-" + ANSI_RESET);
        else   System.out.println(ANSI_YELLOW + count + " items were found" + ANSI_RESET);

    }
    
        @Override
    public void displayByType() {
        int count = 0;
        System.out.println("Enter your type's ram to display: ");
        String s = sc.nextLine();
        header();
        
        for (RAMItem it : this) {
            if(it.getType().equalsIgnoreCase(s)){
                System.out.println(it);
                count += 1;
            }
        }
        if(count == 0) System.out.println(ANSI_RED + "                -EMPTY!-" + ANSI_RESET);
        else   System.out.println(ANSI_YELLOW + count + " items were found" + ANSI_RESET);
    }

    @Override
    public void displayByBus() {
        int count = 0;
        System.out.println("Enter your bus's ram to display: ");
        String s = sc.nextLine();
        header();
        
        for (RAMItem it : this) {
            if(it.getBus().equalsIgnoreCase(s)){
                System.out.println(it);
                count += 1;
            }
        }   
        if(count == 0) System.out.println(ANSI_RED + "                -EMPTY!-" + ANSI_RESET);
        else   System.out.println(ANSI_YELLOW + count + " items were found" + ANSI_RESET);
    }

    @Override
    public void displayByBrand() {
        int count = 0;
        System.out.println("Enter your brand's ram to display: ");
        String s = sc.nextLine();
        header();
        
        for (RAMItem it : this) {
            if(it.getBrand().equalsIgnoreCase(s)){
                System.out.println(it);
                count += 1;
            }
        }
        if(count == 0) System.out.println(ANSI_RED + "                -EMPTY!-" + ANSI_RESET);
        else   System.out.println(ANSI_YELLOW + count + " items were found" + ANSI_RESET);
    }

    @Override
    public void loadData() { //load data from file
        String path = "src\\data\\items.dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\data\\items.dat"))) {
            ArrayList<RAMItem> items = (ArrayList<RAMItem>) ois.readObject();
            this.addAll(items);
        }catch(IOException | ClassNotFoundException e){
             System.out.println(ANSI_RED + "Failed to load! " + e.getMessage()  + ANSI_RESET);
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
    public String[] readTypeFile() {
        String paths = "src\\data\\RamTypes.txt";
        try {
            FileReader fr = new FileReader(paths);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            if(line != null ) return line.split(", ");
        } catch (FileNotFoundException ex) {
            System.out.println(ANSI_RED + "Failed to read!" + ANSI_RESET);
        } catch (IOException ex) {
            System.out.println(ANSI_RED + "Failed to read!" + ANSI_RESET);
        }
        return new String[0];
    }

    @Override
    public String[] readSpeedFile() {
        String paths = "src\\data\\BusSpeeds.txt";
        try{
            FileReader fr = new FileReader(paths);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            if(line != null) return line.split(", ");
        }catch(FileNotFoundException ex) {
            System.out.println(ANSI_RED + "Failed to read!" + ANSI_RESET);
        } catch (IOException ex) {
            System.out.println(ANSI_RED + "Failed to read!" + ANSI_RESET);
        }
        return new String[0];
    }
    
    @Override
    public void newFunction() {
        Collections.sort(this, (RAMItem it1, RAMItem it2) -> {
            return Integer.compare(Integer.parseInt(it2.getBus()), Integer.parseInt(it1.getBus()));
        });
        
        System.out.println("Enter year: ");
        int x = sc.nextInt();
        
        int i = 0;
        int [] yearIT = new int[this.size()];
        for (RAMItem it : this) {
            String[] y = it.getProduction_month_year().split("/");
            yearIT[i] = Integer.parseInt(y[1]);
            i++;
        }
        int j = 0;
        header();
        for(RAMItem it : this){
            if(x < yearIT[j]){
                System.out.println(it);
            }
            j++;
        }
    }
    
    @Override
    public void header() {
        System.out.println( ANSI_PURPLE + "| Code | Type | Bus  |  Brand |Quantity|  MFG  |Active|" + ANSI_RESET); 
    }
    //       ("|%6s|%6s|%6s|%6s|%4d|%10s|", code, type, bus, brand, quantity, production_month_year);

//        Collections.sort(this, (RAMItem it1, RAMItem it2 ) -> Integer.compare(Integer.parseInt(it1.getBus()), Integer.parseInt(it2.getBus())));
//        header();
//        for (RAMItem it : this) {
//            System.out.println(it);
//        }

}
