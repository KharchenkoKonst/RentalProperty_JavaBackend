package com.pv41.rentalproperty.service;

import com.pv41.rentalproperty.dto.AdvertisementDto;
import com.pv41.rentalproperty.model.Advertisement;

import java.util.List;

public interface AdvertisementService {

    void addAdvertisement(AdvertisementDto advertisementDto);

    List<AdvertisementDto> getAdvertisementsByCurrentUser();

    List<AdvertisementDto> getAll();



}
