package com.pv41.rentalproperty.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdvertisementRequestDto {
    private String propertyType;
    private String region;
    private String address;
    private Integer apartmentNumber;
    private Integer areaSummary;
    private Integer areaLiving;
    private Integer roomsCount;
    private Integer pricePerMonth;
    private Integer deposit;
    private Integer floorNumber;
    private Integer floorTotal;
    private String furniture;
    private String accommodations;
    private String additionalDescription;
    private String contactPhoneNumber;
    private List<byte[]> images;
}
