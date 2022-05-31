package com.pv41.rentalproperty.service;

import com.pv41.rentalproperty.dto.AdvertisementRequestDto;
import com.pv41.rentalproperty.dto.AdvertisementResponseDto;
import com.pv41.rentalproperty.dto.mappers.AdvertisementMapper;
import com.pv41.rentalproperty.model.Advertisement;
import com.pv41.rentalproperty.model.Image;
import com.pv41.rentalproperty.model.Status;
import com.pv41.rentalproperty.repository.AdvertisementRepository;
import com.pv41.rentalproperty.repository.ImageRepository;
import com.pv41.rentalproperty.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final AdvertisementMapper advertisementMapper;
    private final MinioService minioService;

    public AdvertisementServiceImpl(
            AdvertisementRepository advertisementRepository,
            UserRepository userRepository,
            ImageRepository imageRepository, AdvertisementMapper advertisementMapper,
            MinioService minioService) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.advertisementMapper = advertisementMapper;
        this.minioService = minioService;
    }

    @Override
    public void addAdvertisement(AdvertisementRequestDto advertisementRequestDto) {
        Advertisement advertisement = advertisementMapper.advertisementRequestDtoToEntity(advertisementRequestDto);
        advertisement.setUser(userRepository.findByLogin(getCurrentLogin()));
        advertisement.setStatus(Status.ACTIVE);
        advertisementRepository.save(advertisement);

        if (advertisementRequestDto.getImages() != null) {
            for (int i = 0; i < advertisementRequestDto.getImages().size(); i++) {
                String imageUrl = UUID.randomUUID().toString();
                minioService.saveToStorage(advertisementRequestDto.getImages().get(i), imageUrl);
                Image imageEntity = new Image();
                imageEntity.setUrl(imageUrl);
                imageEntity.setAdvertisement(advertisement);
                imageEntity.setStatus(Status.ACTIVE);
                imageRepository.save(imageEntity);
            }
        }
    }

    @Override
    public List<AdvertisementResponseDto> getAdvertisementsByUser(String username) {
        List<AdvertisementResponseDto> advertisementResponseDtoList = new ArrayList<>();
        advertisementRepository.findByUser(userRepository.findByLogin(username)).forEach(it -> {
            AdvertisementResponseDto advertisementResponseDto = advertisementMapper.advertisementEntityToResponseDto(it);
            advertisementResponseDtoList.add(advertisementResponseDto);
        });
        return advertisementResponseDtoList;
    }

    @Override
    public List<AdvertisementResponseDto> getAll() {
        List<AdvertisementResponseDto> advertisementResponseDtoList = new ArrayList<>();
        advertisementRepository.findAll().forEach(it -> {
            AdvertisementResponseDto advertisementResponseDto = advertisementMapper.advertisementEntityToResponseDto(it);
            advertisementResponseDtoList.add(advertisementResponseDto);
        });
        return advertisementResponseDtoList;
    }


    private String getCurrentLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
