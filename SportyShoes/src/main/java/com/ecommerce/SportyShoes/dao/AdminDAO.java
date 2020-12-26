package com.ecommerce.SportyShoes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.SportyShoes.entity.Admin;

public interface AdminDAO extends JpaRepository<Admin, Long> {
	
	
	@Query(value="select * from Admin where admin_id =?1 and admin_pwd= ?2" ,nativeQuery = true)
	public Admin authenticate(String AdminId,String AdminPwd);
	
	@Query(value="select * from admin where ID= ?1", nativeQuery = true)
	public Admin getAdminById(Long admin_id);
	
	/*
	 * @Query(value=("update Admin set admin_pwd=:admin_pwd where ID=:id")) public
	 * void updatePwd(Admin admin);
	 */

}
