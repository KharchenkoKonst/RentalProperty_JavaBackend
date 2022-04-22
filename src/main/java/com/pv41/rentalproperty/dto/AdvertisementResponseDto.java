package com.pv41.rentalproperty.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdvertisementResponseDto {
    private String region;
    private List<String> imagesUrl;
    private String ownerUsername;
}
