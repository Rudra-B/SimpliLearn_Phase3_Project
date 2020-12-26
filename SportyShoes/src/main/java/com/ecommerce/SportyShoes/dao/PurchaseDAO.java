package com.ecommerce.SportyShoes.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.SportyShoes.entity.Purchase;

public interface PurchaseDAO extends CrudRepository<Purchase, Long> {
	
	@Modifying
	@Query("update Purchase u set u.userId = ?1, u.date = ?2, u.total = ?3 where u.ID = ?4")
	public int  updatePurchase(long userId,Date date,BigDecimal total,long uid);
	
	
	
	@Query(value="select * from Purchase where user_id = ?1", nativeQuery = true)
	public List<Purchase> getAllPurchasesByUserID(long userId);
	
	@Query(value="select * from purchase", nativeQuery = true)
	public List<Purchase> getAllItems();	

}
