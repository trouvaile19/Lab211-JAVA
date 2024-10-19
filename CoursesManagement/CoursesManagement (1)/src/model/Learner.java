package model;
public class Learner {
    private String learnerID;
    private String leanerName;
    private String DOB;
    private double score;
    private String nameCourse;
    private String state;

    public Learner(String learnerID, String leanerName, String DOB, double score, String nameCourse) {
        this.learnerID = learnerID;
        this.leanerName = leanerName;
        this.DOB = DOB;
        this.score = score;
        this.nameCourse = nameCourse;
        this.setState();
    }

    public String getLearnerID() {
        return learnerID;
    }

    public void setLearnerID(String learnerID) {
        this.learnerID = learnerID;
    }

    public String getLeanerName() {
        return leanerName;
    }

    public void setLeanerName(String leanerName) {
        this.leanerName = leanerName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public double getScore() {
        this.setState();
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getState() {
        if(this.score <= 2) return "false";
        else return "pass";
    }

    public void setState() {
        if (this.score > 2) {
            this.state = "pass";
        } else {
            this.state = "fail";
        }
    }
    

    @Override
    public String toString() {
        return String.format("|%-4s|%-12s|%-10s|%5.2f|%-9s|%-5s|", learnerID, leanerName, DOB, score, nameCourse, state);

    }
    
    
}
