package com.example.controller;

import com.example.model.Drugs;
import com.example.model.Manufacturer;
import com.example.service.DrugsService;
import com.example.service.ManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping( value = "/drugs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DrugsController {

    DrugsService drugsService;
    ManufacturerService manufacturerService;

    @GetMapping
    public String findAll(Model model) {
        List<Drugs> drugs = drugsService.findAll();
        model.addAttribute("drugs", drugs);
        return "drugs/drugs-list";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("drug", new Drugs());
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "drugs/drug-new";
    }

    @PostMapping
    public String create(@ModelAttribute("drug") Drugs drugs){
        drugsService.createDrugs(drugs);
        return "redirect:/drugs";
    }

}
