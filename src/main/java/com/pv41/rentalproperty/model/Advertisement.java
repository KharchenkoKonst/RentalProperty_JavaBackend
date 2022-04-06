package com.pv41.rentalproperty.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "advertisements")
@Data
public class Advertisement extends BaseEntity {

    @Column(name = "region")
    private String region;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> imageURLs;
}
