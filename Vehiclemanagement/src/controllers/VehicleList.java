package controllers;

// @trouvaile19
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import models.IVehicleList;
import models.Vehicle;

public class VehicleList extends ArrayList<Vehicle> implements IVehicleList {

    @Override
    public void addVehicle() { //Add new vehicle
        Scanner sc = new Scanner(System.in);
        System.out.print("\nInput ID vehicle: ");
        String id = sc.nextLine();
        System.out.print("Input vehicle's name: ");
        String name = sc.nextLine();
        System.out.print("Input vehicle's color: ");
        String color = sc.nextLine();
        System.out.print("Input vehicle's brand: ");
        String brand = sc.nextLine();
        System.out.print("Input vehicle's type: ");
        String type = sc.nextLine();
        System.out.print("Input vehicle's price: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Input vehicle's year: ");
        int year = Integer.parseInt(sc.nextLine());
        if (price < 0 || year < 0 || year > 2025) {
            System.out.println("Failed Added! ");
            return;
        }
        Vehicle vehicle = new Vehicle(id, name, color, brand, type, price, year);
        this.add(vehicle);
        System.out.println("Successfully Added!");
        this.saveToFile();

    }

    @Override
    public void checkExist() { // Check	to exist Vehicle
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        VehicleList list = this.readFile();
        if (list.isEmpty()) {
            System.out.println("Information is blank!");
            return;
        }
        System.out.print("Input id you want to check: ");
        String id = sc.nextLine();
        for (Vehicle vh : list) {
            if (vh.getId().equals(id)) {
                System.out.println("Exist vehicle.");
                return;
            }
        }
        System.out.println("No found vehicle!");
    }

    @Override
    public void update() { //Update vehicle's information
        VehicleList list = this.readFile();

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter id to update: ");
        String id = sc.nextLine();

        for (Vehicle veh : list) {
            if (veh.getId().equals(id)) {
                System.out.print("Input new name: ");
                String name = sc.nextLine();
                System.out.print("Input new color: ");
                String color = sc.nextLine();
                System.out.print("Input new price: ");
                double price = sc.nextInt();
                sc.nextLine();
                if (price <= 0) {
                    System.out.println("Invalid price! Try again.");
                    return;
                }
                System.out.print("Enter new brand: ");
                String brand = sc.nextLine();
                System.out.print("Enter new type: ");
                String type = sc.nextLine();
                System.out.print("Enter new year: ");
                int year = sc.nextInt();
                if (year < 0 || year > 2025) {
                    System.out.println("Invalid year of vehicle! Try again");
                }
                Vehicle vehicle = new Vehicle(id, name, color, brand, type, price, year);

                this.set(list.indexOf(veh), vehicle);
                System.out.println("Updated: " + vehicle.toString());
                this.saveToFile();
                return;
            }
        }
        System.out.println("Vehicle does not exist.");
    }

    @Override
    public void delete() { //delete vehicle
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter id to delete: ");
        String id = scan.nextLine();
        VehicleList list = this.readFile();
        for (Vehicle veh : list) {
            if (veh.getId().equals(id)) {
                System.out.print("Are you sure to delete this vehicle? (Press 'y' = 'Yes' / 'n' = 'No'): ");
                String ans = scan.nextLine();
                if ("y".equals(ans)) {
                    this.remove(list.indexOf(veh));
                    System.out.println("Sucessfully Deleted");
                }
                this.saveToFile();
                return;
            }
        }
        System.out.println("This vehicle ID does not exist.");
    }

    @Override
    public void searchName() { //search by name_vehicle
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        System.out.println("Enter vehicle's name you want to search: ");
        String findName = sc.nextLine();
        // read list from vehicle.dat file
        VehicleList list = this.readFile();
        // sort list following descending name
        // Iterate over the veh objects in the list to check exist vehicle's name
        for (Vehicle veh : list) {
            if (veh.getName().contains(findName)) {
                System.out.println(veh.toString());
                cont = false;
            }
        }
        if (cont) {
            System.out.println("No vehicle's name found");
        }
    }

    @Override
    public void searchId() { // search by id_vehicle
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        System.out.print("Enter vehicle's Id you want to search: ");
        String findId = sc.nextLine();
        VehicleList list = this.readFile();// read list saving on the file
        // Iterate over the veh objects in the list to check exist vehicle's id
        for (Vehicle veh : list) {
            if (veh.getId().contains(findId)) {
                System.out.println(veh.toString());
                cont = false;
            }
        }
        if (cont) {
            System.out.println("No vehicle's ID found");
        }
    }

    @Override
    public void displayList() { //
        VehicleList list = this.readFile();
        for (Vehicle veh : list) {
            System.out.println(veh.toString());
        }
    }

    @Override
    public void displayDescendingPrice() {
        VehicleList list = this.readFile();
        list.sort(Comparator.comparingDouble(Vehicle::getPrice).reversed());
        System.out.println("\nAll of vehicle descending by price: ");
        for (Vehicle veh : list) {
            System.out.println(veh.toString());
        }
    }

    @Override
    public void saveToFile() {
        try {
            FileOutputStream fileOuput = new FileOutputStream("src\\data\\VehicleList.dat");
            ObjectOutputStream write = new ObjectOutputStream(fileOuput);
            write.writeObject(this);
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Failed to Save ");
        }
    }

    @Override
    public void printAll() {
        VehicleList list = this.readFile();
        try {
            FileWriter fileWriter = new FileWriter("src\\data\\Showroom.txt");
            for (Vehicle veh : list) {
                String txt = veh.toString();
                fileWriter.write(txt + "\n");
            }
            fileWriter.close();
            System.out.println("Printed file");
        } catch (IOException e) {
            System.out.println("Failed to print");
        }

    }

    @Override
    public void printDescendingPrice() {
        VehicleList list = this.readFile();
        try {
            FileWriter fileWriter = new FileWriter("src\\data\\Showroom.txt");
            list.sort(Comparator.comparingDouble(Vehicle::getPrice).reversed());

            for (Vehicle veh : list) {
                String txt = veh.toString();
                fileWriter.write(txt + "\n");
            }
            fileWriter.close();
            System.out.println("Printed file");
        } catch (IOException e) {
            System.out.println("Failed to print");
        }
    }
    @Override
    public VehicleList readFile() {
        VehicleList result = new VehicleList();
        try (FileInputStream fileInput = new FileInputStream("src\\data\\vehicleList.dat");
                ObjectInputStream read = new ObjectInputStream(fileInput)) {
            result = (VehicleList) read.readObject();
            read.close();
        } catch (EOFException eof) {
            System.out.println("Failed to read. (BLANK!)");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Failed to read");
        }
        return result;
    }
}