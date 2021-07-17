package com.alevel.dao;

import com.alevel.model.Drugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsDAO extends JpaRepository<Drugs, Integer> {

}
