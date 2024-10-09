package view;

import control.ItemList;
import java.util.Scanner;
import model.IRamList;

public class Main {

    public static void main(String[] args) {
        
        MenuView menu = new MenuView();
        IRamList list = new ItemList();
        
        list.loadData();
        boolean isReturnMenu = true;
        while (isReturnMenu) {
            
            menu.availableData();
            menu.mainMenu();
            try {
                Scanner sc = new Scanner(System.in);
                int choose = Integer.parseInt(sc.nextLine());
                if (choose >= 0 & choose < 8) {
                    switch (choose) {
                        case 1:
                            do{
                                menu.availableData();
                                list.addItem();
                                menu.contiOrNot();
                            }while(menu.isContinue(sc));
                            break;
                            
                        case 2:
                            do{
                                menu.subSearchMenu();
                                int op = Integer.parseInt(sc.nextLine());
                                if(op == 1) list.searchType();
                                if(op == 2) list.searchBus(); 
                                if(op == 3) list.searchBrand(); 
                                if(op == 0) break;
                                if(op < 0 && op > 3)  System.out.println(ItemList.ANSI_RESET + 
                                        "Invalid number! Try again!" + ItemList.ANSI_RED);
                                menu.contiOrNot();
                            }while(menu.isContinue(sc));
                            break;
                        case 3:
                            do{
                                list.updateItemInfo();
                                menu.contiOrNot();
                            } while (menu.isContinue(sc));
                            break;
                        case 4:
                            do{
                                list.deleteItem();
                                menu.contiOrNot();
                            }while(menu.isContinue(sc));
                            break;

                        case 5:
                            list.displayAll();
                            do{
                                menu.subDisplayMenu();
                                int op = Integer.parseInt(sc.nextLine());
                                if(op == 1) list.displayByType();
                                if(op == 2) list.displayByBus();
                                if(op == 3) list.displayByBrand();
                                if(op == 0) break;
                                if(op < 0 && op > 3)  System.out.println(ItemList.ANSI_RESET + 
                                        "Invalid number! Try again!" + ItemList.ANSI_RED);
                                menu.contiOrNot();
                            }while(menu.isContinue(sc));
                            break;                            
                        case 6:
                            do{
                                list.savetoFile();
                                menu.contiOrNot();
                            } while(menu.isContinue(sc));
                            break;
                        case 7:
                            do{
                                list.newFunction();
                                menu.contiOrNot();
                            } while(menu.isContinue(sc));
                            break;
                        case 0:
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(ItemList.ANSI_RED + "Try again! Your number is unvalid!" + ItemList.ANSI_RESET);
            }
        }
    }

}
