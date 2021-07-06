package com.example.service.serviceImpl;

import com.example.dao.ManufacturerDAO;
import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerDAO manufacturerDAO;

    public ManufacturerServiceImpl(ManufacturerDAO manufacturerDAO) {
        this.manufacturerDAO = manufacturerDAO;
    }

    @Override
    public void createManufacturer(Manufacturer manufacturer) {
        manufacturerDAO.save(manufacturer);
    }
}
