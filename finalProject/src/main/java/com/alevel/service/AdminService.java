package com.alevel.service;

import com.alevel.model.Admin;

public interface AdminService {
    void create (Admin admin);
    Admin findAdminByEmail (String adminName);
}
