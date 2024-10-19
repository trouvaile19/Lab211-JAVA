package controllers;

import Interfaces.ICourseList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.Course;
import msgColor.Color;
import validator.Validator;

public class CourseList implements ICourseList {
    ArrayList<Course> courseList = new ArrayList<>();
    
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }    
    
    Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();
    Color color = new Color();
    
    @Override
    public void addCourse() {
        String ID;
        boolean checkUnique;
        do {
            ID = validator.inputCourseID();
            checkUnique = false;        
            for (Course cs : courseList) {
                if (ID.equals(cs.getCourseID())) {
                    Color.msgRed("This ID has already existed!");
                    checkUnique = true; 
                    break;
                }
            }
        } while (checkUnique);
        String name = validator.inputCourseName(0);
        String type = validator.inputCourseType(0);
        String title = validator.inputCourseTitle(0);
        String beginDate = validator.inputBeginDate(0);
        String endDate = validator.inputEndDate(0);
        double tuition = validator.inputFee(0);
        courseList.add(new Course(ID, name, type, title, beginDate, endDate, tuition));
    }

    @Override
    public void updateCourse() {
        System.out.println("Enter Course code to update: ");
        String codeSearch = sc.nextLine();
        String name, type, title, beginDate, endDate;
        double fee;
        Color.alert("Enter to keep unchanged");
        for (Course cs : courseList) {
            if(cs.getCourseID().equals(codeSearch)){
                name = validator.inputCourseName(1);
                type = validator.inputCourseType(1);
                title = validator.inputCourseTitle(1);
                beginDate = validator.inputBeginDate(1);
                endDate = validator.inputEndDate(1);
                fee = validator.inputFee(1);
                if(!name.isEmpty()){
                    cs.setNameCourse(name);
                }
                if(!type.isEmpty()){
                    cs.setType(type);
                }
                if(!title.isEmpty()){
                    cs.setTitle(title);
                }        
                if(!beginDate.isEmpty()){
                    cs.setBeginDate(beginDate);
                }
                if(!endDate.isEmpty()){
                    cs.setEndDate(endDate);
                }
                if(fee != -1){
                    cs.setTutionFee(fee);
                }
                Color.courseHeader();
                System.out.println(Color.green +  "Updated: "+ Color.reset + cs);
                Color.msgGreen("Sucessfully updated!");
                return;
            }
        }
        Color.error("No Item Found!");
    }

    @Override
    public void deleteCourse() {
        Color.alert("Enter the topic code to delete: ");
        String search = sc.nextLine();
        
        for (Course cs : courseList) {
            if(search.equals(cs.getCourseID())){
                Color.alert("Founded! Are you sure to delete this topic? [Y]es or [N]o: ");
                String ans = sc.nextLine();
                if (ans.equalsIgnoreCase("Y") ){
                    courseList.remove(cs);
                    Color.msgGreen("Sucessfully Deleted!");
                }else{
                    Color.alert("Canceled to delete Topic!");
                }
            }
        }
    }

    @Override
    public void DisplayCourse() {
        Collections.sort(courseList, (Course cs1, Course cs2) -> {
            String[] part1 = cs1.getBeginDate().trim().split("/");
            String[] part2 = cs2.getBeginDate().trim().split("/");
            
            int year1 = Integer.parseInt(part1[1]);
            int year2 = Integer.parseInt(part2[1]);
            
            int month1 = Integer.parseInt(part1[0]);
            int month2 = Integer.parseInt(part2[0]);
            
            if(year1 != year2 ){
                return Integer.compare(year1, year2);
            }else return Integer.compare(month1, month2);
        });
        
        Color.courseHeader();
        for (Course cs : courseList) {
            System.out.println(cs);
        }
    }
    
    public void nameSearch(String name){
        Color.courseHeader();
        boolean find = false;
        for (Course cs : courseList) {
            if(cs.getNameCourse().equalsIgnoreCase(name)){
                System.out.println(cs);
                find = true;
            }
        }
        if(!find) Color.alert("Not found!");
    }
    public void searchStatus(){
        int count = 0;
        Collections.sort(courseList, (Course c1, Course c2)->{
            return Double.compare(c1.getTutionFee(), c2.getTutionFee());
        });
        
        System.out.println("Enter status to search: ");
        String status = sc.nextLine();
        Color.courseHeader();
        for (Course cs : courseList) {
            if(cs.getType().equalsIgnoreCase(status)){
                System.out.println(cs);
                count += 1;
            }
        }
        if(count == 0){
            Color.msgRed("Not found status sastify the requirement");
        } else System.out.println(count + " Courses Founded!");

    }
}
