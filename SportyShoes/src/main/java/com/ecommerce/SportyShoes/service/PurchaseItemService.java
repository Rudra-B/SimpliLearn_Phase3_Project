package com.ecommerce.SportyShoes.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.SportyShoes.dao.PurchaseItemDAO;
import com.ecommerce.SportyShoes.entity.PurchaseItem;

@Service
public class PurchaseItemService {
	@Autowired
	PurchaseItemDAO purchaseItemDAO;
	
	@Transactional
	public void updateItem(PurchaseItem pItem)
	{
		
		purchaseItemDAO.save(pItem);
		purchaseItemDAO.updateItem(pItem.getPurchaseId(), pItem.getProductId(), pItem.getUserId(), pItem.getRate(), pItem.getQty(), pItem.getID());
		
	}

	public List<PurchaseItem> getAllItemsByPurchaseId(long pid) {
		
		return purchaseItemDAO.getAllPurchaseItemByPurchaseID(pid);
	}
	

}
