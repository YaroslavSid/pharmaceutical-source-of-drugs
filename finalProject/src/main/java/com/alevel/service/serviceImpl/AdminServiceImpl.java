package com.alevel.service.serviceImpl;

import com.alevel.dao.AdminDAO;
import com.alevel.model.Admin;
import com.alevel.service.AdminService;
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
