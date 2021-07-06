package com.example.service.serviceImpl;

import com.example.dao.AdminDAO;
import com.example.model.Admin;
import com.example.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;

    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public void create(Admin admin) {
        admin.setRole("admin");
        adminDAO.save(admin);
    }

    @Override
    public Admin findAdminByEmail(String adminEmail) {
        return adminDAO.findAdminByEmail(adminEmail);
    }

}
