package com.ecommerce.SportyShoes.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Purchase {
	
	
	
	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long ID;
	
	@Column(name="user_id")
	private long userId;
	
	@Column
	private Date date;
	
	@Column
	private BigDecimal total;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	

}
