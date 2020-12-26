package com.ecommerce.SportyShoes.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.SportyShoes.entity.PurchaseItem;

public interface PurchaseItemDAO extends CrudRepository<PurchaseItem, Long>{
	
	@Modifying
	@Query("update PurchaseItem u set u.purchaseId = ?1, u.productId = ?2, u.userId =?3, u.rate =?4, u.qty =?5 where u.ID = ?6")
	public int  updateItem(long purchaseID,long productId,long userId,BigDecimal rate, int qty,long ID);
	
	
	@Query(value="select * from PurchaseItem where purchase_id = ?1", nativeQuery = true)
	public List<PurchaseItem> getAllPurchaseItemByPurchaseID( long pid);

}
