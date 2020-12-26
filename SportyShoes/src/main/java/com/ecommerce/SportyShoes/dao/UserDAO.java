package com.ecommerce.SportyShoes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.SportyShoes.entity.User;

public interface UserDAO extends CrudRepository<User, Long>  {

	
	@Query(value="select * from user where emailid = ?1 and pwd = ?2", nativeQuery = true)
	public User authenticate(String eid,String pwd);
	
	
	@Query(value="select * from user where emailid = ?1",nativeQuery = true)
	public User getUserByEmailId(String emailId);
	
	
	@Query(value="select * from user where ID = ?1", nativeQuery = true)
	public User getUserByID(long uid);
	
	
	@Query(value="select * from user", nativeQuery = true)
	public List<User> getAllUsers();
}
