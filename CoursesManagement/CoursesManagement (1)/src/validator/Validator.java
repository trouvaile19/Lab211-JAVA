package validator;

import msgColor.Color;
import java.util.Scanner;

public class Validator {
    Scanner sc = new Scanner(System.in);
    
    public boolean validateID(String id, String regrex){ 
        return id.matches(regrex); //format for id
    }
    public boolean validateType(String type, String s1, String s2){
        return (type.equalsIgnoreCase(s1) || type.equalsIgnoreCase(s2)) ;
    }
    public boolean validateDate(String date){
        String regrex = "^(0[1-9]|1[0-2])/([0-9]{4})$";
        return date.matches(regrex);
    }
    public boolean validateDob(String date){
        String regrex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$";
        return date.matches(regrex);
    }
    public boolean validateName(String name){
        String regrex = "^[a-zA-Z\\s]*$";
        return name.matches(regrex);
    }
    public boolean validateTitle(String title){
        String regrex = "^[a-zA-Z\\s]*$";
        return title.matches(regrex);
    }
    public boolean validateFee(double fee){
        return (fee >= 0); 
    }
    public boolean validateScore(double score){
        return score >= 0;
    }
    public boolean validateDuration(int duration){
        return duration > 0;
    }
    
    
//-------------------------------------------------------------------------------------------------------//
//    For Topic:
    public String inputTopicID(){
        String id;
        while(true){
            System.out.print("Input Topic id ('Txy' <x,y from 0 to 9> ): ");
            id = sc.nextLine();
            if(validateID(id, "T[0-9]{2}")) break;
            Color.msgRed("Invalid ID!");
        }
        return id;
    }
    
    public String inputTopicName(int t){
        String name;
        while(true){
            System.out.print("Input Topic name: ");
            name = sc.nextLine();
            if(t == 1 & name.isEmpty()) return "";
            if(validateName(name)) break;
            Color.msgRed("Invalid name!");
        }
        return name;
    }
    
    public String inputTopicType(int t){
        String type;
        while(true){
            System.out.print("Input Topic type('short'/'long'): ");
            type = sc.nextLine();
            if(t == 1 & type.isEmpty()) return "";
            if(validateType(type, "long", "short")) break;
            Color.msgRed("Invalid type!");
        }
        return type;
    }
    
    public String inputTopicTitle(int t){
        String title;
        while(true){
            System.out.print("Input Topic title: ");
            title = sc.nextLine();
            if(t == 1 & title.isEmpty()) return "";
            if(validateTitle(title)) break;
            Color.msgRed("Invalid name");
        }
        return title;
    }
    
    public int inputDurationTopic(int t){
        int duration;
        while(true){
            System.out.print("Input Topic duration (days): ");
            try{
            duration = Integer.parseInt(sc.nextLine());
            if(t == 1 & String.valueOf(duration).isEmpty()) return -1;
            if(validateDuration(duration)) break;
            }catch(NumberFormatException e){
                Color.msgRed("Invalid duration! Try Again!");
            }
        }
        return duration;
    }
//    ---------------------------------------------------------
    // FOR Course
    public String inputCourseID(){
        String id;
        while(true){
            System.out.print("Input Course id (Cxy <x,y from 0 to 9>): ");
            id = sc.nextLine();
            if(validateID(id, "C[0-9]{2}")) break;
            Color.msgRed("Invalid ID!");
        }
        return id;
    }
    
    public String inputCourseName(int t){
        String name;
        while(true){
            System.out.print("Input Course name: ");
            name = sc.nextLine();
            if(t == 1 & name.isEmpty()) return "";
            if(validateName(name)) break;
            Color.msgRed("Invalid name!");
        }
        return name;
    }
    
    public String inputCourseTitle(int t){
        String title;
        while(true){
            System.out.print("Input Course title: ");
            title = sc.nextLine();
            if(t == 1 & title.isEmpty()) return "";
            if(validateTitle(title)) break;
            Color.msgRed("Invalid name");
        }
        return title;
    }
    
    public String inputCourseType(int t){
        String type;
        while(true){
            System.out.print("Input Course type (online/offline): ");
            type = sc.nextLine();
            if(t == 1 & type.isEmpty()) return "";
            if(validateType(type, "online", "offline")) break;
            Color.msgRed("Invalid type!");
        }
        return type;
    }
    
    public String inputBeginDate(int t){
        String date;
        while(true){
            System.out.print("Input Course begin date (xx/yyyy): ");
            date = sc.nextLine();
            if(t == 1 & date.isEmpty()) return "";
            if(validateDate(date)) break;
            Color.msgRed("Invalid Date!");
        }
        return date;
    }
    
    public String inputEndDate(int t){
    String date;
        while(true){
            System.out.print("Input Course End date (xx/yyyy): ");
            date = sc.nextLine();
            if(t == 1 & date.isEmpty()) return "";
            if(validateDate(date)) break;
            Color.msgRed("Invalid Date!");
        }
        return date;
    }
    
    public double inputFee(int t){
        double fee;
        while(true){
            System.out.print("Input Course fee: ");
            try{
            fee = Double.parseDouble(sc.nextLine());
            if(t == 1 & String.valueOf(fee).isEmpty()) return -1;
            if(validateFee(fee)) break;
            }catch(NumberFormatException e){
                Color.msgRed("Invalid Fee!");
            }
        }
        return fee;
    }
// ------------------------------------------------------------------
//    For Learner
    public String inputLearnerID(){
        String id;
        while(true){
            System.out.print("Input Learner id('Lxy' <x,y from 1 to 9> ): ");
            id = sc.nextLine();
            if(validateID(id, "L[0-9]{2}")) break;
            Color.msgRed("Invalid ID!");
        }
        return id;
    }
    
    public String inputLearnerName(){
        String name;
        while(true){
            System.out.print("Input Learner name: ");
            name = sc.nextLine();
            if(validateName(name)) break;
            Color.msgRed("Invalid name!");
        }
        return name;
    }
    
    public String inputDOB(){
        String dob;
        while(true){
            System.out.print("Input Learner date of birth(xy/yy/zzzz): ");
            dob = sc.nextLine();
            if(validateDob(dob)) break;
            Color.msgRed("Invalid date of birth!");
        }
        return dob;
    }
    
    public double inputScore(){
        double score;
        while(true){
            System.out.print("Input Learner score: ");
            score = Double.parseDouble(sc.nextLine());
            if(validateScore(score)) break;
            Color.msgRed("Invalid Score!");
        }
        return score;
    }
    
    public String inputCourse(){
        String course;
        while(true){
            System.out.print("Input Course name of learner: ");
            course = sc.nextLine();
            if (validateName(course)) break;
            Color.msgRed("Invalid!");
        }
        return course;
    }
}
