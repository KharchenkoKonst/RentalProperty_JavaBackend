package com.pv41.rentalproperty.service;

import com.pv41.rentalproperty.dto.AdvertisementRequestDto;
import com.pv41.rentalproperty.dto.AdvertisementResponseDto;

import java.util.List;

public interface AdvertisementService {

    void addAdvertisement(AdvertisementRequestDto advertisementRequestDto);

    List<AdvertisementResponseDto> getAdvertisementsByUser(String login);

    List<AdvertisementResponseDto> getAll();

}
