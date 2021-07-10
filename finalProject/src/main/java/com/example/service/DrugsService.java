package com.example.service;

import com.example.model.Drugs;

import java.util.List;


public interface DrugsService {

    List<Drugs> findAll();

    void createDrugs (Drugs drugs);

}
