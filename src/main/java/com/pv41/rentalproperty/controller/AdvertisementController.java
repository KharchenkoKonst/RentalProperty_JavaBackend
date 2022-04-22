package com.pv41.rentalproperty.controller;

import com.pv41.rentalproperty.dto.AdvertisementRequestDto;
import com.pv41.rentalproperty.dto.AdvertisementResponseDto;
import com.pv41.rentalproperty.service.AdvertisementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping
    public void addAdvertisement(@RequestBody AdvertisementRequestDto advertisementRequestDto) {
        advertisementService.addAdvertisement(advertisementRequestDto);
    }

    @GetMapping(value = "/{username}")
    public List<AdvertisementResponseDto> getAdvertisementsByUser(@PathVariable String username) {
        return advertisementService.getAdvertisementsByUser(username);
    }

    @GetMapping
    public List<AdvertisementResponseDto> getAllAdvertisements() {
        return advertisementService.getAll();
    }

}
