package com.example.service.serviceImpl;

import com.example.dao.DrugsDAO;
import com.example.model.Drugs;
import com.example.service.DrugsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DrugsServiceImpl implements DrugsService {

    DrugsDAO drugsDAO;

    @Override
    public List<Drugs> findAll() {
        return drugsDAO.findAll();
    }

    @Override
    public void createDrugs(Drugs drugs) {

    }
}