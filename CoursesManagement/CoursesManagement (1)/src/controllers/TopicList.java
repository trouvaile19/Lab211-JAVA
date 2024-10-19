package controllers;

import Interfaces.ITopicList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import model.Topic;
import msgColor.Color;
import validator.Validator;

public class TopicList implements ITopicList {
    ArrayList<Topic> topicList = new ArrayList<>();
    
    public ArrayList<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(ArrayList<Topic> topicList) {
        this.topicList = topicList;
    }
    
    
    Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();
    Color color = new Color();
    
    @Override
    public void addTopic() {
        String ID;
        boolean checkUnique;
        do {
            ID = validator.inputTopicID();
            checkUnique = false;        
            for (Topic tc : topicList) {
                if (ID.equals(tc.getTopicID())) {
                    Color.msgRed("This ID has already existed!");
                    checkUnique = true; 
                    break;
                }
            }
        } while (checkUnique);
        String name = validator.inputTopicName(0);
        String type = validator.inputTopicType(0);
        String title = validator.inputTopicTitle(0);
        int duration = validator.inputDurationTopic(0);
        topicList.add(new Topic(ID, name, type, title, duration));
    }

    @Override
    public void updateTopic() {
        System.out.println("Enter topic code to update: ");
        String codeSearch = sc.nextLine();
        String name, type, title;
        int duration;
        Color.alert("Enter to keep unchanged");
        for (Topic topic : topicList) {
            if(topic.getTopicID().equals(codeSearch)){
                name = validator.inputTopicName(1);
                type = validator.inputTopicType(1);
                title = validator.inputTopicTitle(1);
                duration = validator.inputDurationTopic(1);
                if(!name.isEmpty()){
                    topic.setNameTopic(name);
                }
                if(!type.isEmpty()){
                    topic.setType(type);
                }
                if(!title.isEmpty()){
                    topic.setTitle(title);
                }        
                if(!String.valueOf(duration).isEmpty()){
                    topic.setDuration(duration);
                }
                Color.topicHeader();
                System.out.println(Color.green + "Updated: " + Color.reset + topic);
                Color.msgGreen("Sucessfully updated!");
                return;
            }
        }
        Color.error("No Item Found!");
    }

    @Override
    public void deleteTopic() {
        Color.alert("Enter the topic code to delete: ");
        String search = sc.nextLine();
        
        for (Topic tp : topicList) {
            if(search.equals(tp.getTopicID())){
                Color.alert("Founded! Are you sure to delete this topic? [Y]es or [N]o: ");
                String ans = sc.nextLine();
                if (ans.equalsIgnoreCase("Y") ){
                    topicList.remove(tp);
                    Color.msgGreen("Sucessfully Deleted!");
                }else{
                    Color.alert("Canceled to delete Topic!");
                }
            }
        }
    }

    @Override
    public void DisplayAllTopics() {
        Collections.sort(topicList, (t1, t2) -> (t1.getNameTopic().compareTo(t2.getNameTopic())));
        Iterator<Topic> it = topicList.iterator();
        Color.topicHeader();
        while(it.hasNext()){
            Topic tp = it.next();
            System.out.println(tp);
        }
    }
    
    public void searchTopic(String name){
        Color.topicHeader();
        boolean find = false;
        for (Topic tp : topicList) {
            if(tp.getNameTopic().equalsIgnoreCase(name)){
                System.out.println(tp);
                find = true;
            }
        }
        if(!find) Color.alert("Not found!");
    }
    
}
