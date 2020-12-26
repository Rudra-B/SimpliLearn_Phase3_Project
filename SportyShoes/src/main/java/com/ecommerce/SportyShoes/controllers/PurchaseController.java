package com.ecommerce.SportyShoes.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.SportyShoes.entity.Product;
import com.ecommerce.SportyShoes.entity.Purchase;
import com.ecommerce.SportyShoes.entity.PurchaseItem;
import com.ecommerce.SportyShoes.service.ProductService;
import com.ecommerce.SportyShoes.service.PurchaseItemService;
import com.ecommerce.SportyShoes.service.PurchaseService;

@Controller
public class PurchaseController {
	
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private PurchaseItemService purchaseItemService;

	  @RequestMapping(value = "/memberpurchases", method = RequestMethod.GET)
	    public String memberpurchases(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  return "login";
		  }
		  long userId = (long) session.getAttribute("user_id");
		  
		  List<Purchase> list = purchaseService.getAllItemsByUserId(userId);
		  
		  BigDecimal total = new BigDecimal(0.0);
		  // map purchase items to each purchase for display
		  HashMap<Long, String> mapItems = new HashMap<Long, String>();
		  
		  for(Purchase purchase: list) {
			  total = total.add(purchase.getTotal());
			  
			  List<PurchaseItem> itemList = purchaseItemService.getAllItemsByPurchaseId(purchase.getID());
			  StringBuilder sb = new StringBuilder("");
			  for(PurchaseItem item: itemList) {
				  Product product = productService.getProductById(item.getProductId());
				  if (product != null) {
					  sb.append(product.getName() + ", " + 
						  	item.getQty() + " units @" + item.getRate() + " = " 
						  	+ item.getPrice() + "<br>");
				  }
			  }
			  mapItems.put(purchase.getID(), sb.toString());
		  }
		  map.addAttribute("totalAmount", total);
		  map.addAttribute("list", list);
		  map.addAttribute("mapItems", mapItems);
		  map.addAttribute("pageTitle", "SPORTY SHOES - YOUR ORDERS");
		  
	       return "purchases"; 
	    }		  

}
