package com.pv41.rentalproperty.dto.mappers;

import com.pv41.rentalproperty.dto.AdvertisementDto;
import com.pv41.rentalproperty.model.Advertisement;

public class AdvertisementMapper {
    public Advertisement advertisementDtoToEntity(AdvertisementDto advertisementDto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setRegion(advertisementDto.getRegion());
        return advertisement;
    }

    public AdvertisementDto advertisementEntityToDto(Advertisement advertisement) {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setRegion(advertisement.getRegion());
        return advertisementDto;
    }

}
