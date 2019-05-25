package com.thoughtworks.nho.olsapi.camp;

import javax.persistence.Entity;

@Entity
public class Task extends EntityDTO {
    public Task(String title, String description) {
        super(title, description);
    }
}
