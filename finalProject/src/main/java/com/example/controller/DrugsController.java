package com.example.controller;

import com.example.model.Drugs;
import com.example.service.DrugsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping( "/drugs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DrugsController {

    DrugsService drugsService;

    @GetMapping
    public String findAll(Model model) {
        List<Drugs> drugs = drugsService.findAll();

        model.addAttribute("drugs", drugs);
        return "drugs/drugs-list";
    }

}
