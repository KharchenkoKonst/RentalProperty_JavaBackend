package com.pv41.rentalproperty.controller;

import com.pv41.rentalproperty.dto.AdvertisementDto;
import com.pv41.rentalproperty.service.AdvertisementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/advertisements/")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping(value = "add")
    public void addAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        advertisementService.addAdvertisement(advertisementDto);
    }

    @GetMapping(value = "getByUser")
    public List<AdvertisementDto> getAdvertisementsByUser() {
        return advertisementService.getAdvertisementsByCurrentUser();
    }

    @GetMapping(value = "getAll")
    public List<AdvertisementDto> getAllAdvertisements(){
        return advertisementService.getAll();
    }
}
