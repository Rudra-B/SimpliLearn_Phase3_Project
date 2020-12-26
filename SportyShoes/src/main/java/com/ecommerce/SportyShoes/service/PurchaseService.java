package com.ecommerce.SportyShoes.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.SportyShoes.dao.PurchaseDAO;
import com.ecommerce.SportyShoes.entity.Purchase;

@Service
public class PurchaseService {
	
	@Autowired
	PurchaseDAO purchaseDAO;
	@Transactional
	public long  updatePurchase(Purchase purchase)
	{
		return purchaseDAO.save(purchase).getID();
		//return purchaseDAO.updatePurchase(purchase.getUserId(), purchase.getDate(), purchase.getTotal(), purchase.getID());
	}
	
	
	
	public List<Purchase> getAllItemsByUserId(long userId) {
		
		return purchaseDAO.getAllPurchasesByUserID(userId);
		
	}



	public List<Purchase> getAllItems() {
	
		return purchaseDAO.getAllItems();
	}

	
}
