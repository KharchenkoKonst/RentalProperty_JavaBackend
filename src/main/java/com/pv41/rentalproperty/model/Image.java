package com.pv41.rentalproperty.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image extends BaseEntity {

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    private Advertisement advertisement;
}
