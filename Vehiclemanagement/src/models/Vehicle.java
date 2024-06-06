/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

public class Vehicle implements Serializable { // chuyển đối tượng Vehicle thành chuỗi byte
    private String id;
    private String name;
    private String color;    
    private String brand ;
    private String type;
    private double price;
    private int year;
    
    public Vehicle(){} //constructer with no parameters
    // constructer with parameters
    public Vehicle(String id, String name, String color, String brand, String type, double price, int year) {
        this.id = id;
        this.name = name;
        this.color = color;   
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.year = year;
    }
    // Get & Set value
    public String getId() {   return id;}
    public void setId(String id) {  this.id = id;}

    public String getName() {  return name;}
    public void setName(String name) {    this.name = name;}

    public String getColor() {   return color;}
    public void setColor(String color) {   this.color = color;}

    public String getBrand() {  return brand;}
    public void setBrand(String brand) {    this.brand = brand;}

    public String getType() {   return type;}
    public void setType(String type) {  this.type = type;}
    
    public double getPrice() {  return price;  }
    public void setPrice(double price) {    this.price = price;}
    
    public int getYear() {  return year;}
    public void setYear(int year) {    this.year = year;}
    
    @Override
    public String toString() {
        return "id =" + id + ", name " + name + ", color=" + color + ", brand=" + brand + ", type=" + type + ", price=" + price + ", year=" + year ;
    }
    
}   
