package com.pv41.rentalproperty.dto;

import lombok.Data;

@Data
public class AdvertisementDto {
    private String region;
    private byte[] image;
    private String ownerUsername;
}
