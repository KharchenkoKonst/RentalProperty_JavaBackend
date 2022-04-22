package com.pv41.rentalproperty.service;

import java.util.Optional;

public interface MinioService {

    void saveToStorage(byte[] data, String url);
/*
    Optional<byte[]> getFromStorage(String url);
    String getMinioUrl();
*/
}