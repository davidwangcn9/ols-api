package com.thoughtworks.nho.olsapi.camp;

import com.thoughtworks.nho.olsapi.TaskService;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
import java.util.UUID;


@Entity
public class TrainingCamp{
    @Id
    private UUID id;
    private String title;
    private String description;
    private long createdTime;
    private String startTime;
    @Transient
    private List<Task> tasks;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public TrainingCamp(String title, String startTime, String description) {
        this.id=UUID.randomUUID();
        this.title = title;
        this.startTime=startTime;
        this.description = description;
        this.createdTime = System.currentTimeMillis();
        this.tasks= TaskService.getTasks(getCampIndex(null));
    }

    public TrainingCamp(){
        this.id=UUID.randomUUID();
        this.createdTime = System.currentTimeMillis();
        this.tasks= TaskService.getTasks(getCampIndex(null));
    }


    private int getCampIndex(UUID id){
        return 0;
    }
}
