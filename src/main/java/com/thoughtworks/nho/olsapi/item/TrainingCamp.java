package com.thoughtworks.nho.olsapi.item;

import com.thoughtworks.nho.olsapi.TaskService;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TrainingCamp extends Entity{
    private String startTime;
    private List<Task> tasks;

    public String getStartTime() {
        return startTime;
    }

    public List<Task> getTasks() {
        return tasks;
    }


    public TrainingCamp(String title, String startTime, String description) {
        super(title, description);
        this.startTime=startTime;
        this.tasks= TaskService.getTasks(getCampIndex(null));
    }

    private int getCampIndex(UUID id){
        return 0;
    }
}
