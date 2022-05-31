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
        advertisement.setPropertyType(advertisementRequestDto.getPropertyType());
        advertisement.setRegion(advertisementRequestDto.getRegion());
        advertisement.setAddress(advertisementRequestDto.getAddress());
        advertisement.setApartmentNumber(advertisementRequestDto.getApartmentNumber());
        advertisement.setAreaSummary(advertisementRequestDto.getAreaSummary());
        advertisement.setAreaLiving(advertisementRequestDto.getAreaLiving());
        advertisement.setRoomsCount(advertisementRequestDto.getRoomsCount());
        advertisement.setPricePerMonth(advertisementRequestDto.getPricePerMonth());
        advertisement.setDeposit(advertisementRequestDto.getDeposit());
        advertisement.setFloorNumber(advertisementRequestDto.getFloorNumber());
        advertisement.setFloorTotal(advertisementRequestDto.getFloorTotal());
        advertisement.setFurniture(advertisementRequestDto.getFurniture());
        advertisement.setAccommodations(advertisementRequestDto.getAccommodations());
        advertisement.setAdditionalDescription(advertisementRequestDto.getAdditionalDescription());
        advertisement.setContactPhoneNumber(advertisementRequestDto.getContactPhoneNumber());
        return advertisement;
    }

    public AdvertisementResponseDto advertisementEntityToResponseDto(Advertisement advertisement) {
        AdvertisementResponseDto advertisementResponseDto = new AdvertisementResponseDto();
        advertisementResponseDto.setId(advertisement.getId());
        advertisementResponseDto.setPropertyType(advertisement.getPropertyType());
        advertisementResponseDto.setRegion(advertisement.getRegion());
        advertisementResponseDto.setAddress(advertisement.getAddress());
        advertisementResponseDto.setApartmentNumber(advertisement.getApartmentNumber());
        advertisementResponseDto.setAreaSummary(advertisement.getAreaSummary());
        advertisementResponseDto.setAreaLiving(advertisement.getRoomsCount());
        advertisementResponseDto.setPricePerMonth(advertisement.getPricePerMonth());
        advertisementResponseDto.setDeposit(advertisement.getDeposit());
        advertisementResponseDto.setFloorNumber(advertisement.getFloorNumber());
        advertisementResponseDto.setFloorTotal(advertisement.getFloorTotal());
        advertisementResponseDto.setFurniture(advertisement.getFurniture());
        advertisementResponseDto.setAccommodations(advertisement.getAccommodations());
        advertisementResponseDto.setAdditionalDescription(advertisement.getAdditionalDescription());
        advertisementResponseDto.setContactPhoneNumber(advertisement.getContactPhoneNumber());
        advertisementResponseDto.setOwnerFirstName(advertisement.getUser().getFirstName());
        advertisementResponseDto.setOwnerLastName(advertisement.getUser().getLastName());
        List<String> imagesUrl = new ArrayList<>();
        for (Image image : advertisement.getImageURLs()) {
            imagesUrl.add(image.getUrl());
        }
        advertisementResponseDto.setImagesUrl(imagesUrl);
        return advertisementResponseDto;
    }
}
