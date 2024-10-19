package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Course;
import model.Learner;
import model.Topic;
import msgColor.Color;

public class FileHandle {
    //hàm nguyên mẫu 
    public <T> void saveToFile(ArrayList<T> list, String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        for (T item : list) {
            writer.write(item.toString());
            writer.newLine(); 
        }
            Color.msgGreen("Sucessfully save to file");
        } catch (IOException e) {
            Color.msgRed("Failed to save" + e.getMessage());
        }
    }
    // load data from Course.txt
     public ArrayList loadCourses(String paths) {
        ArrayList<Course> courses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(paths))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                String courseID = parts[1].trim();
                String name = parts[2].trim();
                String type = parts[3].trim();
                String title = parts[4].trim();
                String beginDate = parts[5].trim();
                String endDate = parts[6].trim();
                double tutionFee = Double.parseDouble(parts[7].trim());

                Course course = new Course(courseID, name, type, title, beginDate, endDate, tutionFee);
                courses.add(course);
            }
        } catch (IOException e) {
            Color.msgRed("Failed to read!");
        }

        return courses;
    }
    // load data from Learner.txt
    public ArrayList<Learner> loadLearners (String paths){
        ArrayList<Learner> learners = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(paths))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.trim().split("\\|");
                if (parts.length < 6) continue;
                String learnerID = parts[1].trim();
                String name = parts[2].trim();
                String DOB = parts[3].trim();
                double score = Double.parseDouble(parts[4].trim());
                String nameCourse = parts[5].trim();
                
                Learner learner = new Learner(learnerID, name, DOB, score, nameCourse);
                learners.add(learner);
            }
        }catch (IOException e){
            Color.msgRed("Failed to read!");
        }
        return learners; 
    }
     
    // load data from Topic.txt
    public ArrayList<Topic> loadTopics(String paths){
        ArrayList<Topic> topics = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(paths))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("\\|");
                
                String topicID = parts[1].trim();
                String name = parts[2].trim();
                String type = parts[3].trim();
                String title = parts[4].trim();
                int duration = Integer.parseInt(parts[5].trim());
                
                Topic tp = new Topic(topicID, name, type, title, duration);
                topics.add(tp);
            }
            
        }catch(IOException e ){
            Color.msgRed("Failed to read!");
        }
        return topics;
    }

    
}
