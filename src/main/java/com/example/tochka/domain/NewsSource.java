package com.example.tochka.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="news_uri")
public class NewsSource {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    @Size(max = 36)
    private String id;


    @Column(name = "news_uri")
    private String uri;

    @Column(name = "news_type")
    private String type;

    @Column(name = "news_imgType")
    private String imgType;
    @Column(name = "news_href")
    private String href;

    @Column(name = "news_time")
    private String time;

    @Column(name = "news_rss")
    private Boolean rss;

    @Column(name="news_uri_id")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "news_id")
    private Set<NewLenta> newLentas = new HashSet<>();

    public NewsSource(String uri, Boolean rss, String type,  Collection<NewLenta> newLentas) {
        this.uri = uri;
        this.newLentas= new HashSet<>(newLentas);
        this.type=type;
        this.rss=rss;
    }

    protected NewsSource() {
        /* as required by ORM/JPA */
    }

    public NewsSource(String uri, Boolean rss, String type, String imgType, String href, String time, List<NewLenta> newLentas) {
        this.uri = uri;
        this.newLentas= new HashSet<>(newLentas);
        this.type=type;
        this.imgType=imgType;
        this.href=href;
        this.rss=rss;
        this.time=time;
    }
}
