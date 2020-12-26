package com.ecommerce.SportyShoes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "admin") 
public class Admin {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "admin_id")
	private String adminId;
	
	@Column(name = "admin_pwd")
	private String AdminPwd;

	
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return AdminPwd;
	}

	public void setAdminPwd(String pwd) {
		this.AdminPwd = pwd;
	}
	
	
	

}
