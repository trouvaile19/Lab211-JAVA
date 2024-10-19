package view;

import controllers.CourseList;
import controllers.FileHandle;
import controllers.LearnerList;
import controllers.TopicList;
import java.util.Scanner;
import msgColor.Color;

public class CoursesManagement {

    public static void main(String[] args) {
        Color color = new Color();
        Scanner sc = new Scanner(System.in);
        MenuView menu = new MenuView();
        
        TopicList tpList = new TopicList();
        CourseList csList = new CourseList();
        LearnerList lnList = new LearnerList();
        FileHandle file = new FileHandle();
        
        //load data from file 
        tpList.setTopicList(file.loadTopics("src\\data\\Topic.txt"));
        csList.setCourseList(file.loadCourses("src\\data\\Course.txt"));
        lnList.setLnList(file.loadLearners("src\\data\\Learner.txt"));
        

        boolean returnMenu = true;
        while (returnMenu) {
            menu.mainMenu();
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1: // manage Topic
                    menu.subTopicsMenu();
                    int option1 = Integer.parseInt(sc.nextLine());
                    if (option1 == 1) {
                        do {
                            tpList.addTopic();
                            menu.contiOrNot();
                        } while (menu.isContinue(sc));
                    }else if (option1 == 2){
                            tpList.updateTopic();
                    }else if (option1 == 3){
                            tpList.deleteTopic();
                    }else if (option1 == 4){
                            tpList.DisplayAllTopics();
                    }else{
                        Color.msgRed("Invalid choice!");
                    }

                    break;
                case 2: // manage course
                    menu.subCourseMenu();
                    int option2 = Integer.parseInt(sc.nextLine());

                    if (option2 == 1) {
                        do {
                            csList.addCourse();
                            menu.contiOrNot();
                        } while (menu.isContinue(sc));
                    }else if (option2 == 2){
                        csList.updateCourse();
                    }else if (option2 == 3){
                        csList.deleteCourse();
                    }else if (option2 == 4){
                        csList.DisplayCourse();
                    }
                    break;

                case 3:
                    menu.subLearnerMenu();
                    int option3 = Integer.parseInt(sc.nextLine());
                    if (option3 == 1){
                        do{
                            lnList.addLearner();
                            menu.contiOrNot();
                        }while (menu.isContinue(sc));
                    } else if (option3 == 2 ){
                        lnList.scoreUpdate();
                    } else if (option3 == 3){
                        lnList.displayLearnerInfo();
                    }

                    break;
                case 4:
                    menu.subSearchMenu();
                    int option4 = Integer.parseInt(sc.nextLine());
                    if (option4 == 1 ){
                        System.out.println("Enter name topic to search: ");
                        String name = sc.nextLine();
                        tpList.searchTopic(name);

                    }
                    if (option4 == 2){
                        System.out.println("Name of course to search: ");
                        String name = sc.nextLine();
                        csList.nameSearch(name);
                    }
                    break;
                case 5:
                    file.saveToFile(csList.getCourseList(), "src\\data\\Course.txt");
                    file.saveToFile(lnList.getLearnerList(), "src\\data\\Learner.txt");
                    file.saveToFile(tpList.getTopicList(), "src\\data\\Topic.txt");
                    break;
                    
                case 6:
                    csList.searchStatus();
                    break;
                case 0:
                    returnMenu = false;
                    break;
                default:
                    Color.msgRed("Invalid choice!");
            }
        }
    }

}
