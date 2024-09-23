package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import model.Bike;
import model.IBikeFunction;

public class BikeList extends ArrayList<Bike> implements IBikeFunction {

    Scanner sc = new Scanner(System.in);

    @Override
    public void newProduct() {  //Function 1: Create a Product
        String id;
        String name;
        String brandId;
        String categoryId;
        int year = 0;
        double price = 0.0;

        boolean check;
        do {
            check = false;
            System.out.println("\nEnter ID product: ");
            id = sc.nextLine();
            for (Bike bike : this) {
                if (id.equals(bike.getId())) {
                    System.out.println("This id has already existed!");
                    check = true;
                }
            }
        } while (check);

        while (true) {
            System.out.println("Enter name product <xe***> (* is an number from 0 to 9): ");
            name = sc.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be left blank!");
            }
            if (name.matches("^xe\\d{3}$")) {
                break;
            } else {
                System.out.println("You must input correct name's format!");
            }
        }
        while(true){
            System.out.println("Enter brand ID <B***> (* is an number from 0 to 9): ");
            brandId = sc.nextLine();
            // Check if exist in BrandID.txt
            boolean checkExist = false; 
            for (String readID : readBrandFile()) {
                if(readID.equalsIgnoreCase(brandId)){
                    System.out.println("    -This brandID already exists!-");
                    checkExist = true;
                }
            }
            if(!checkExist && brandId.matches("^B\\d{3}")) break;            
            else System.out.println("-You must input correct brand id format!-");      
        }
        while(true){
            System.out.println("Enter category ID <C***> (* is an number from 0 to 9): ");
            categoryId = sc.nextLine();
            // check if exist in categoryID.txt
            boolean checkExist = false;
            for(String readId : readCategoryFile()){
                if(readId.equalsIgnoreCase(categoryId)){
                    System.out.println("   -This cateforyID already exists!-");
                    checkExist = true;
                }
            }
            if(!checkExist && categoryId.matches("^C\\d{3}")) break;
            else System.out.println("-You must input correct categoryid format!-");
        }
        while (true) {
            try {
                System.out.println("Enter model year: ");
                year = Integer.parseInt(sc.nextLine());
                if (year > 2024 || year <= 1900) {
                    System.out.println("Uncorrect year (1900< year < 2024)");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Model year must be a number!");
            }
        }

        while (true) {
            try {
                System.out.println("Enter price (maxium 1000.00): ");
                price = Double.parseDouble(sc.nextLine());
                if (price <= 0) {
                    System.out.println("Price must be positive!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price must be a number");
            }
        }
        Bike bike = new Bike(id, name, brandId, categoryId, year, price);
        this.add(bike);
        System.out.println("Sucessfully Added");
    }

    @Override
    public void searchingProduct() { //Fuction 2: Search product by name & ascending by price
        System.out.println("\nEnter name product you want to search: ");
        String search = sc.nextLine();
        int isExist = 0;
        
        header();
        Collections.sort(this, (Bike b1, Bike b2) -> Double.compare(b1.getListPrice(), b2.getListPrice()));
        for (Bike bike : this) {
            if (bike.getName().contains(search)) {
                System.out.println(bike);
                isExist += 1;
            }
        }
        if (isExist == 0) {
                System.out.println("        -Having no any product-\n");
        }
    }

    @Override
    public void updateInfo() { //Update Product information
        System.out.println("\nEnter id product to update: ");
        String id = sc.nextLine();
        int searchCount = 0;
        for (Bike bike : this) {
            if (id.equalsIgnoreCase(bike.getId())) {
                subUpdate(id, bike);
                System.out.println("Sucessfully updated!");
                searchCount += 1;
            }
        }
        if(searchCount != 0)   System.out.println("Product does not exist!\n");
    }

    @Override
    public void deleteProduct() {
        System.out.println("\nEnter id product to delete: ");
        String id = sc.nextLine();

        Iterator<Bike> it = this.iterator();
        boolean found = false;
        while (it.hasNext()) {
            Bike bike = it.next();
            if (id.equalsIgnoreCase(bike.getId())) {
                it.remove();
                System.out.println("Successfully deleted");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product does not exist!");
        }
    }

    @Override
    public void saveToFile() {
        String path = "src\\data\\Products.txt";
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            try (BufferedWriter wr = new BufferedWriter(fw)) {
                for (Bike bike : this) {
                    wr.write(bike.toString());
                    wr.newLine();
                }
            }
            System.out.println("Successfully save");
        } catch (IOException e) {
            System.out.println("Failed to save file");
        }
    }

    @Override
    public void readProductsFile() { //Function 6: print all from file
        //sort data first: order by list price descending. 
        //If the same price, then order by name ascending
        Collections.sort(this, (Bike b1, Bike b2) -> {
            int comparison = Double.compare(b2.getListPrice(), b1.getListPrice());
            if (comparison == 0){
                return b1.getName().compareTo(b2.getName());
            }else return comparison;
        }) ;
        // read file
        System.out.println("");
        header();
        String path = "src\\data\\Products.txt";
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            try (BufferedReader br = new BufferedReader(fr)) {
                String line;
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }
            System.out.println("\n   -Sucessfully print the file-   ");

        } catch (EOFException eof) {
            System.out.println("Failed to print file");
        } catch (IOException ex) {
            System.out.println("Failed to read!");
        }
    }

    @Override // just check if is empty -> set old value or not set new value
    public void subUpdate(String id, Bike bike) {
        boolean check = false;
        while (!check) {
            try {
                String oldName = bike.getName();
                String oldBrandID = bike.getBrandId();
                String oldCategoryID = bike.getCategoryId();
                int oldModelYear = bike.getModelYear();
                double oldPrice = bike.getListPrice();

                System.out.println("\nEnter new name product: ");
                bike.setName(sc.nextLine());
                if (bike.getName().isEmpty()) { // just check if user enter an empty?
                    bike.setName(oldName);   //empty -> set old info; no -> update
                }

                System.out.println("Enter new brand ID: ");
                bike.setBrandId(sc.nextLine());
                if (bike.getBrandId().isEmpty()) {
                    bike.setBrandId(oldBrandID);
                }
                System.out.println("Enter new category ID: ");
                bike.setCategoryId(sc.nextLine());
                if (bike.getCategoryId().isEmpty()) {
                    bike.setCategoryId(oldCategoryID);
                }

                while (true) {
                    System.out.println("Enter new model year: ");
                    bike.setModelYear(Integer.parseInt(sc.nextLine()));
                    if (String.valueOf(bike.getModelYear()).isEmpty()) {
                        bike.setModelYear(oldModelYear);
                    }
                    if (bike.getModelYear() > 2024 || bike.getModelYear() <= 1900) {
                        System.out.println("Uncorrect year (1900< year < 2024)");
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.println("Enter new price: ");
                    bike.setListPrice(Double.parseDouble(sc.nextLine()));
                    if (String.valueOf(bike.getListPrice()).isEmpty()) {
                        bike.setListPrice(oldPrice);
                    }
                    if (bike.getListPrice() <= 0) {
                        System.out.println("Price must be positive!");
                    } else {
                        break;
                    }
                }
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    @Override
    public String [] readCategoryFile() { //read file
        String path = "src\\data\\01_Category.txt";
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            String line;
            List<String> list = new ArrayList<>();
            
            try (BufferedReader br = new BufferedReader(fr)) {
                while((line = br.readLine()) != null){
                    list.add(line);
                }   
            }
            String[] cateIDs = new String[list.size()];
            for(int i = 0; i < list.size(); i++){
                String[] temp = list.get(i).trim().split(", ");
                cateIDs[i] = temp[0];
            }
            return cateIDs;
        } catch (IOException e) {
            System.out.println("Failed to read category.txt!");
            return null;
        }
    }

    @Override
    public String[] readBrandFile() { //read file
        String path = "src\\data\\01_Brand.txt";
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            List<String> list = new ArrayList<>();

            while((line = br.readLine()) != null){
                list.add(line);
            }
            String [] brandIDs = new String[list.size()];
            for(int i = 0; i < list.size(); i++){
                String[] temp = list.get(i).trim().split(", ");
                brandIDs[i] = temp[0];
            }
            return brandIDs;
        } catch (IOException e){
            System.out.println("Failed to read Brand.txt!");
            return null;
        }
    }

    @Override
    public void header() {
        System.out.println("|  ID |  Name  |Brand|Category|Year| Price |");
    }
//        return String.format("|%5s|%8s|%5s|%5s|%4d|%5.2f|", id, name, brandId, categoryId, modelYear, listPrice);

}
