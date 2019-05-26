package com.thoughtworks.nho.olsapi.camp;

import com.thoughtworks.nho.olsapi.TaskService;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
import java.util.UUID;


public class TrainingCampDto {
    private UUID id;
    private String title;
    private String description;
    private long createdTime;
    private String startTime;
    private List<Task> tasks;

}
