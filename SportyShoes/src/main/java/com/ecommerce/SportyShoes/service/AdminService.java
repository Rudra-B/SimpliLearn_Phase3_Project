package com.ecommerce.SportyShoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.SportyShoes.dao.AdminDAO;
import com.ecommerce.SportyShoes.entity.Admin;



@Service
public class AdminService {
	@Autowired
	AdminDAO adminDAO;
	
	public Admin authenticate(String adminId, String adminPwd)
	{
		return adminDAO.authenticate(adminId, adminPwd);
	}

	public Admin getAdminById(Long admin_id) {
		
		return adminDAO.getAdminById(admin_id);
	}

	public void updatePwd(Admin admin) {
		
		
		adminDAO.save(admin);
	}
	

}
