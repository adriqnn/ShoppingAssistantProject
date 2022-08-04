package com.example.shoppingassistant.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_picture")
public class ProductPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag", nullable = false, unique = true)
    private String tag;

    @Column(name = "location", nullable = false)
    private String location;

    public ProductPicture() {
    }

    public ProductPicture(String tag, String location) {
        this.tag = tag;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public ProductPicture setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ProductPicture setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public ProductPicture setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
