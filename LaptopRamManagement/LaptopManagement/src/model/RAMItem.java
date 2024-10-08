package model;

import java.io.Serializable;

public class RAMItem implements Serializable{
    private String code;
    private String type;
    private String bus;
    private String brand; 
    private int quantity;
    private String production_month_year;
    private boolean isActive;

    public RAMItem(String code, String type, String bus, String brand, int quantity, String production_month_year, boolean isActive) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.isActive = isActive;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }
    

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduction_month_year() {
        return production_month_year;
    }

    public void setProduction_month_year(String production_month_year) {
        this.production_month_year = production_month_year;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    

    @Override
    public String toString() {
        return String.format("|%6s|%6s|%6s|%6s|%8d|%7s|", code, type, bus, brand, quantity, production_month_year);
    }
    
    
}
