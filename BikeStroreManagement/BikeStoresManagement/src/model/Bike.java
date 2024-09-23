
package model;

public class Bike {
    private String id;
    private String name;
    private String brandId;
    private String categoryId;
    private int modelYear;
    private double listPrice;
     
    public Bike(){}

    public Bike(String id, String name, String brandId, String categoryId, int modelYear, double listPrice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBrandId() { return brandId; }

    public void setBrandId(String brandId) { this.brandId = brandId; }

    public String getCategoryId() { return categoryId; }

    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public int getModelYear() { return modelYear; }

    public void setModelYear(int modelYear) { this.modelYear = modelYear; }

    public double getListPrice() { return listPrice; }

    public void setListPrice(double listPrice) { this.listPrice = listPrice; }

    @Override
    public String toString() {
        return String.format("|%5s|%8s|%5s|%8s|%4d|%5.2f|", id, name, brandId, categoryId, modelYear, listPrice);
    }
}
