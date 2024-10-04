
package model;

public interface IBikeFunction {
    void newProduct();
    
    void searchingProduct();
    
    void updateInfo();
    
    void deleteProduct();
    
    void saveToFile();
    
    //submethod of updateinfo method to input & validate new information
    void subUpdate(String id, Bike oldBike); 
    
    void readProductsFile();
    
    String [] readCategoryFile();
    
    String [] readBrandFile();
    
    void header();
    
    void storagetxt();
}

