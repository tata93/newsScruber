package com.example.tochka.domain;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name="news")
public class NewLenta {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    @Size(max = 36)
    private String id;


    @Column(name="title")
    private String title;

    @Column(name="link")
    private String link;

    @Column(name="imgType")
    private String imgType;

    @Column(name="time")
    private String time;

    @Column(name="description", columnDefinition ="text" )
    private String description;



    public NewLenta(String title, String link, String description ) {
        this.title = title;
        this.link = link;
        this.description=description;
    }

    protected NewLenta() {
        /* as required by ORM/JPA */
    }

    public NewLenta( String a, String link,String imgType, String time) {
        this.title = a;
        this.link = link;
        this.imgType = imgType;
        this.time = time;
        this.description=a;
    }
}
