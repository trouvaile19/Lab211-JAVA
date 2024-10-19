package msgColor;
public class Color {
    public static String reset = "\u001B[0m";
    public static String black = "\u001B[30m";
    public static String red = "\u001B[31m";
    public static String green = "\u001B[32m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_PURPLE = "\u001B[35m";
    public static String ANSI_CYAN = "\u001B[36m";
    public static String ANSI_WHITE = "\u001B[37m";
    
    public static void msgRed(String s){
        System.out.println(red + s + reset);
    }
    public static void msgGreen(String s){
        System.out.println(green + s + reset);
    }
    
    public static void msgBlue(String s){
        System.out.println(ANSI_BLUE + s + reset);
    }
    
    public static void error(String s){
        System.out.println(red + "[ERROR] " +reset + s);
    }
    public static void success(String s){
        System.out.println(green + "[SUCCESS] " + reset + s);
    }
    
    public static void alert(String s){
        System.out.println(ANSI_YELLOW + "[ALERT] " + reset + s);
    }
    
    public static void topicHeader(){
        System.out.println(ANSI_PURPLE + "|Code|   Topic name  | Type  |      Title     |Duration|" + reset);
    }
    
    public static void courseHeader(){
        System.out.println(ANSI_PURPLE + "|Code |  CourseName |  Type   |      Title      |BeginDate| EndDate |  Tution |" + reset);
    }
    
    public static void learnerHeader(){
        System.out.println(ANSI_PURPLE + "|Code|Learner Name|   DOB    | GPA |Course ID|State|" + reset);
    }
    //|%4s|%12s|%10s|%1.2f|%10s|", learnerID, leanerName, DOB, score, nameCourse
}
