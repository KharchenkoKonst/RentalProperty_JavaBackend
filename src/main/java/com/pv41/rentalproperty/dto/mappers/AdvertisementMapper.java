package com.pv41.rentalproperty.dto.mappers;

import com.pv41.rentalproperty.dto.AdvertisementRequestDto;
import com.pv41.rentalproperty.dto.AdvertisementResponseDto;
import com.pv41.rentalproperty.model.Advertisement;
import com.pv41.rentalproperty.model.Image;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementMapper {
    public Advertisement advertisementRequestDtoToEntity(AdvertisementRequestDto advertisementRequestDto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setRegion(advertisementRequestDto.getRegion());
        return advertisement;
    }

    public AdvertisementResponseDto advertisementEntityToResponseDto(Advertisement advertisement) {
        AdvertisementResponseDto advertisementResponseDto = new AdvertisementResponseDto();
        advertisementResponseDto.setRegion(advertisement.getRegion());
        advertisementResponseDto.setOwnerUsername(advertisement.getUser().getUsername());
        List<String> imagesUrl = new ArrayList<>();
        for (Image image : advertisement.getImageURLs()){
            imagesUrl.add(image.getUrl());
        }
        advertisementResponseDto.setImagesUrl(imagesUrl);
        return advertisementResponseDto;
    }

}
