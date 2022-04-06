package com.pv41.rentalproperty.service;

import com.pv41.rentalproperty.dto.AdvertisementDto;
import com.pv41.rentalproperty.dto.mappers.AdvertisementMapper;
import com.pv41.rentalproperty.model.Advertisement;
import com.pv41.rentalproperty.model.Status;
import com.pv41.rentalproperty.repository.AdvertisementRepository;
import com.pv41.rentalproperty.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final AdvertisementMapper advertisementMapper;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, UserRepository userRepository, AdvertisementMapper advertisementMapper) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.advertisementMapper = advertisementMapper;
    }

    @Override
    public void addAdvertisement(AdvertisementDto advertisementDto) {
        Advertisement advertisement = advertisementMapper.advertisementDtoToEntity(advertisementDto);
        advertisement.setUser(userRepository.findByUsername(getCurrentUsername()));
        advertisement.setStatus(Status.ACTIVE);
        advertisementRepository.save(advertisement);
    }

    @Override
    public List<AdvertisementDto> getAdvertisementsByCurrentUser() {
        List<AdvertisementDto> advertisementDtoList = new ArrayList<>();
        advertisementRepository.findByUser(userRepository.findByUsername(getCurrentUsername())).forEach(it -> {
            advertisementDtoList.add(advertisementMapper.advertisementEntityToDto(it));
        });
        return advertisementDtoList;
    }

    @Override
    public List<AdvertisementDto> getAll() {
        List<AdvertisementDto> advertisementDtoList = new ArrayList<>();
        advertisementRepository.findAll().forEach(it -> {
            String ownerUsername = it.getUser().getUsername();
            AdvertisementDto advertisementDto = advertisementMapper.advertisementEntityToDto(it);
            advertisementDto.setOwnerUsername(ownerUsername);
            advertisementDtoList.add(advertisementDto);
        });
        return advertisementDtoList;
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
