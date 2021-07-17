package com.alevel.dao;

import com.alevel.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Admin findAdminByEmail (String adminName);
}
