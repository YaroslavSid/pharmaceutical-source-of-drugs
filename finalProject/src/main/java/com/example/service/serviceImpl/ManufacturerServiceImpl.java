package com.example.service.serviceImpl;

import com.example.dao.ManufacturerDAO;
import com.example.model.Drugs;
import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerDAO manufacturerDAO;

    @Transactional
    @Override
    public void createManufacturer(Manufacturer manufacturer) {
        manufacturerDAO.save(manufacturer);
    }

    @Transactional
    @Override
    public List<Manufacturer> findAll() {
        return manufacturerDAO.findAll();
    }
}
