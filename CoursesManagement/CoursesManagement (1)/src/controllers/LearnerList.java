package controllers;

import Interfaces.ILearnerList;
import java.util.ArrayList;
import java.util.Scanner;
import model.Course;
import model.Learner;
import msgColor.Color;
import validator.Validator;

public class LearnerList implements ILearnerList {
    ArrayList<Learner> lnList = new ArrayList<>();
    
    public ArrayList<Learner> getLearnerList() {
        return lnList;
    }

    public void setLnList(ArrayList<Learner> lnList) {
        this.lnList = lnList;
    }
    //
    CourseList courses = new CourseList();
    
    Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();

    
    @Override
    public void addLearner() {
        String ID;
        boolean checkUnique;
        do {
            ID = validator.inputLearnerID();
            checkUnique = false;        
            for (Learner ln : lnList) {
                if (ID.equals(ln.getLearnerID())) {
                    Color.msgRed("This ID has already existed!");
                    checkUnique = true; 
                    break;
                }
            }
        } while (checkUnique);
        String name = validator.inputLearnerName();
        String DOB = validator.inputDOB();
        double score = validator.inputScore();
        boolean check = true;
        String course;
        do{
            course = validator.inputCourseID();
            for (Course cs : courses.getCourseList()) {
                if(course.equalsIgnoreCase(cs.getCourseID())){
                    check = false;
                    break;
                }else
                    Color.error("Not found CourseID respectively!Try again");
            }
        }while(check);
        String state;
        lnList.add(new Learner(ID, name, DOB, score, course));
    }

    @Override
    public void scoreUpdate() {
        System.out.print("Enter learner code to update score: ");
        String code = sc.nextLine();
        for (Learner ln : lnList) {
            if(code.equalsIgnoreCase(ln.getLearnerID())){
                System.out.print("Enter new score: ");
                ln.setScore(Double.parseDouble(sc.nextLine()));
                System.out.println(ln);
                Color.msgGreen("Successfully updated");
                return;
            }
        }
        Color.msgRed("The learner does not exist!");
    }

    @Override
    public void displayLearnerInfo() {
        int countPass = 0;
        int countFail = 0;
        
        Color.learnerHeader();
        for (Learner ln : lnList) {
            System.out.println(ln);
            if("pass".equals(ln.getState())){
                countPass += 1;
            }else{
                countFail += 1;
            }
        }
        System.out.println("\nThe number of student pass: " + countPass);
        System.out.println("The number of student fail: " + countFail);
    }
    
}
