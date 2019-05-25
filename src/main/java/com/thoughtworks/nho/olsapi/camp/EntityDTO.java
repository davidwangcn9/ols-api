package com.thoughtworks.nho.olsapi.camp;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;


public class EntityDTO {

    @Id
    private UUID id;

    private String title;
    private String description;
    private Timestamp createTime;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public EntityDTO(String title, String description) {
        this.id=UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.createTime = new Timestamp(System.currentTimeMillis());
    }
}
