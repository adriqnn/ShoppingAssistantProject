package com.example.shoppingassistant.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "profile_pictures")
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag", nullable = false)
    private String tag;

    @Column(name = "location", nullable = false)
    private String location;

    public ProfilePicture() {
    }

    public ProfilePicture(String tag, String location) {
        this.tag = tag;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public ProfilePicture setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ProfilePicture setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public ProfilePicture setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
