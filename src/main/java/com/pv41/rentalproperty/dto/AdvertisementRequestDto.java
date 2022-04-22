package com.pv41.rentalproperty.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdvertisementRequestDto {
    private String region;
    private List<byte[]> images;
}
