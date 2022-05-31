package com.pv41.rentalproperty.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "advertisements")
@Data
public class Advertisement extends BaseEntity {

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "region")
    private String region;

    @Column(name = "address")
    private String address;

    @Column(name = "apartment_number")
    private Integer apartmentNumber;

    @Column(name = "area_summary")
    private Integer areaSummary;

    @Column(name = "area_living")
    private Integer areaLiving;

    @Column(name = "rooms_count")
    private Integer roomsCount;

    @Column(name = "price_per_month")
    private Integer pricePerMonth;

    @Column(name = "deposit")
    private Integer deposit;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "floor_total")
    private Integer floorTotal;

    @Column(name = "furniture")
    private String furniture;

    @Column(name = "accommodations")
    private String accommodations;

    @Column(name = "additional_description")
    private String additionalDescription;

    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> imageURLs;
}
