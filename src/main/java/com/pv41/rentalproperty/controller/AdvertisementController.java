package com.pv41.rentalproperty.controller;

import com.pv41.rentalproperty.dto.AdvertisementRequestDto;
import com.pv41.rentalproperty.dto.AdvertisementResponseDto;
import com.pv41.rentalproperty.service.AdvertisementService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/v1/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping
    public ResponseEntity addAdvertisement(@RequestBody AdvertisementRequestDto advertisementRequestDto) {
        try {
            advertisementService.addAdvertisement(advertisementRequestDto);
            return ResponseEntity.ok("Success publish");
        }catch (DataAccessException e){
            return ResponseEntity.badRequest().body("Incorrect input");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error");
        }
    }

    @GetMapping(value = "/{login}")
    public List<AdvertisementResponseDto> getAdvertisementsByUser(@PathVariable String login) {
        return advertisementService.getAdvertisementsByUser(login);
    }

    @GetMapping
    public List<AdvertisementResponseDto> getAllAdvertisements() {
        return advertisementService.getAll();
    }
}
