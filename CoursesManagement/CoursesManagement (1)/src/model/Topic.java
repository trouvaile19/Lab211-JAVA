package model;
public class Topic {
    private String topicID;
    private String nameTopic;
    private String type;
    private String title;
    private int duration;

    public Topic(String topicID, String nameTopic, String type, String title, int duration) {
        this.topicID = topicID;
        this.nameTopic = nameTopic;
        this.type = type;
        this.title = title;
        this.duration = duration;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("|%4s|%15s|%7s|%16s|%8d|", topicID, nameTopic, type ,title, duration);
    }
    
    
}
