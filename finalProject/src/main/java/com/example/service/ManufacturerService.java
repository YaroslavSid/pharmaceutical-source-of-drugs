package com.example.service;

import com.example.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    void createManufacturer (Manufacturer manufacturer);

    List<Manufacturer> findAll();

}
