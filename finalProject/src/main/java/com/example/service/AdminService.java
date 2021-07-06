package com.example.service;

import com.example.model.Admin;

public interface AdminService {
    void create (Admin admin);
    Admin findAdminByEmail (String adminName);
}
