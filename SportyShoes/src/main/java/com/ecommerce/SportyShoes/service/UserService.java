package com.ecommerce.SportyShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.SportyShoes.dao.UserDAO;
import com.ecommerce.SportyShoes.entity.User;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;
	public User authenticate(String emailId, String pwd) {
	
		return userDAO.authenticate(emailId, pwd);
	}
	
	
	public User getUserByEmailId(String emailId) {
		
		return userDAO.getUserByEmailId(emailId);
	}


	public void saveUser(User user) {
	
		userDAO.save(user);
		
	}


	public User getUserById(long uid) {
		
		return userDAO.getUserByID(uid);
	     
		
	}


	public List<User> getAllUsers() {
		
		return userDAO.getAllUsers();
	}
	
	

}
