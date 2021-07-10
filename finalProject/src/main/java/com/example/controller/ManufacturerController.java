package com.example.controller;


import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String addManufacturer (@RequestParam("nameManufacturer") String nameManufacturer) {

        Manufacturer newManufacturer = new Manufacturer();
        newManufacturer.setName(nameManufacturer);

        manufacturerService.createManufacturer(newManufacturer);


        return "manufacturer/add-manufacturer";
    }

}
