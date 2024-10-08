package model;

public interface IRamList {

    void addItem();

    void searchType();

    void searchBus();

    void searchBrand();

    void updateItemInfo();

    void deleteItem();

    void displayAll();

    void loadData();

    void savetoFile();
    // other submethods
    void subUpdate(String code, RAMItem item);
    
    void readTypeFile();
    
    void readSpeedFile();
    
    void header();

}
