package com.realdolmen.redoairproject.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AbstractEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public AbstractEntity() {
    }


    public AbstractEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
