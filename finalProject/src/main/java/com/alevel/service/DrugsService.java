package com.alevel.service;

import com.alevel.model.Drugs;

import java.util.List;


public interface DrugsService {

    List<Drugs> findAll();

    void createDrugs (Drugs drugs);

}
