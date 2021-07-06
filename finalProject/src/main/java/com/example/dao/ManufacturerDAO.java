package com.example.dao;

import com.example.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerDAO extends JpaRepository<Manufacturer, Integer> {

}
