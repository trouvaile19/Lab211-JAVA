package model;
public class Course {
    private String courseID;
    private String nameCourse;
    private String type;
    private String title;
    private String beginDate;
    private String endDate;
    private double tutionFee;

    public Course(String courseID, String nameCourse, String type, String title, String beginDate, String endDate, double tutionFee) {
        this.courseID = courseID;
        this.nameCourse = nameCourse;
        this.type = type;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tutionFee = tutionFee;
    }
    
    

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTutionFee() {
        return tutionFee;
    }

    public void setTutionFee(double tutionFee) {
        this.tutionFee = tutionFee;
    }

    @Override
    public String toString() {
        return String.format("|%-5s|%-13s|%-9s|%-17s|%-9s|%-9s|%-9.2f|", courseID, nameCourse, type, title, beginDate, endDate, tutionFee);
    }     
}
