package com.example.dao;

import com.example.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Admin findAdminByEmail (String adminName);
}
