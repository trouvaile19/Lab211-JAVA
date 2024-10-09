package model;

public interface IRamList {

    void addItem();

    void searchType();

    void searchBus();

    void searchBrand();

    void updateItemInfo();

    void deleteItem();

    void displayAll();
    
    void displayByType(); // show all items having the same type
    
    void displayByBus(); // show all items having the same bus
    
    void displayByBrand(); // show all items having the same brand

    void loadData();

    void savetoFile();
    // other submethods
    void subUpdate(String code, RAMItem item);
    
    String[] readTypeFile();
    
    String[] readSpeedFile();
    
    void header();
    
    void newFunction();

}
