package com.pv41.rentalproperty.repository;

import com.pv41.rentalproperty.model.Advertisement;
import com.pv41.rentalproperty.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByUser(User user);
}
