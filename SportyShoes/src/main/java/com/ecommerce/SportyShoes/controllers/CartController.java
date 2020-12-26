package com.ecommerce.SportyShoes.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.SportyShoes.entity.CartItem;
import com.ecommerce.SportyShoes.entity.Product;
import com.ecommerce.SportyShoes.entity.Purchase;
import com.ecommerce.SportyShoes.entity.PurchaseItem;
import com.ecommerce.SportyShoes.service.ProductService;
import com.ecommerce.SportyShoes.service.PurchaseItemService;
import com.ecommerce.SportyShoes.service.PurchaseService;

@Controller
public class CartController {
	
	
	@Autowired
	ProductService productService;
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	PurchaseItemService purchaseItemService;
	
	Logger logger= LoggerFactory.getLogger(CartController.class);
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model map, javax.servlet.http.HttpServletRequest request) 
    {
		
		logger.info("logging from CartController cart method");
	  // check if user is logged in
	  HttpSession session = request.getSession();
	  if (session.getAttribute("user_id") == null) {
		 
		  map.addAttribute("error", "Error, You need to login before adding items to cart");
	  } else {
		  // if cart is already in session then retrieve it else create a new cart list and 
		  // save it to session
		  List<CartItem> cartItems = new ArrayList<CartItem>();
		  if (session.getAttribute("cart_items") != null)
			  cartItems = (List<CartItem>) session.getAttribute("cart_items");
		  
		  // get total of all cart items
		  BigDecimal totalValue = getCartValue(cartItems);
		  map.addAttribute("cartValue", totalValue);
		  map.addAttribute("cartItems", cartItems);
		  }
	  
	  map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");
        return "cart"; 
    }
	
	

	
	@RequestMapping("/cartadditem")
	public String cartAddItem(Model model, javax.servlet.http.HttpServletRequest request, @RequestParam(value="id", required=true) int productId)
	{
		
		HttpSession session=request.getSession();
		//session.setAttribute("user_id", 2);
		if(session.getAttribute("user_id")==null)
		{
			logger.warn("there is no user session so returnin gto login page");
			model.addAttribute("error", "Error,You need to login before adding items to cart from add");
		}else
		{
			logger.info("user session is active hence adding to cart");
			List<CartItem> cartItems=new ArrayList<>(); 
			if(session.getAttribute("cart_Items")!=null)
			{
				cartItems=(List<CartItem>)session.getAttribute("cart_Items");
			}
			if (isItemInCart(cartItems, productId)) {
				  model.addAttribute("error", "This item is already in your cart");
		}else {
			Product product = productService.getProductById(productId);
			  CartItem item = new CartItem();
			  item.setProductId(productId);
			  item.setQty(1);
			  item.setRate(product.getPrice());
			  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty())); 
			  item.setPrice(dprice); 
			  item.setName(product.getName()); 
			  cartItems.add(item);
			  
			  session.setAttribute("cart_items", cartItems);
		}
		
		}
		
		return "redirect:cart";
	}
	
	
	
	 @RequestMapping(value = "/cartdeleteitem", method = RequestMethod.GET)
	    public String cartDeleteItem(Model model, javax.servlet.http.HttpServletRequest request, 
	    		@RequestParam(value="id", required=true) int id) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  model.addAttribute("error", "Error, You need to login before deleting items from cart");
		  } else {
			  
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  	  
			  for(CartItem item: cartItems) {
				  if (item.getProductId() == id) {
					  cartItems.remove(item);
					  session.setAttribute("cart_items", cartItems);
					  break;
				  }
			   }
		  }	
	        return "redirect:cart"; 
	    }	
	 
	 @RequestMapping(value = "/checkout", method = RequestMethod.GET)
	    public String checkout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before checking out");
		  } else {
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);
		  }
		  map.addAttribute("pageTitle", "SPORTY SHOES - CHECKOUT");
	        return "checkout"; 
	    }
	
	
	 
	 @RequestMapping(value = "/gateway", method = RequestMethod.GET)
	    public String gateway(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before making payment");
		  } else {
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);

		  }
		  
		  map.addAttribute("pageTitle", "SPORTY SHOES - PAYMENT GATEWAY");
	        return "gateway"; 
	    }
	
	
	 @RequestMapping(value = "/completepurchase", method = RequestMethod.GET)
	    public String completePurchase(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before completing purchase");
		  } else {
			  // take items from cart and update the databae 
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  
			  long userId =(long)session.getAttribute("user_id") ;
			  
			  Purchase purchase = new Purchase();
			  purchase.setUserId(userId);
			  purchase.setDate(Calendar.getInstance().getTime());
			  purchase.setTotal(totalValue);
			  long purchaseId = purchaseService.updatePurchase(purchase);
			  
			  for(CartItem item: cartItems) {
				  PurchaseItem pItem = new PurchaseItem();
				  pItem.setPurchaseId(purchaseId);
				  pItem.setProductId(item.getProductId());
				  pItem.setUserId(userId);
				  pItem.setRate(item.getRate());
				  pItem.setQty(item.getQty());
				  pItem.setPrice(item.getPrice());
				  
				  purchaseItemService.updateItem(pItem);
			  }
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);

		  }
		  
	        return "redirect:confirm";  
	    }
	 
	 
	 @RequestMapping(value = "/confirm", method = RequestMethod.GET)
	    public String confirm(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before completing the purchase");
		  } else {
			  // clear items from cart as order has completed 
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("cartValue", totalValue);

			  
			  cartItems.clear();
			  session.setAttribute("cart_items", null);
		  }
		  map.addAttribute("pageTitle", "SPORTY SHOES - PURCHASE CONFIRMATION");
	        return "confirm"; 
	    }	  
	 
	 
	
	
	
	
	private boolean isItemInCart(List<CartItem> list, int item) {
		  boolean retVal = false;
		  
		  for(CartItem thisItem: list) {
			  if (item == thisItem.getProductId()) {
				  retVal = true;
				  break;
			  }
		  }
		  return retVal;
	  } 
	
	private BigDecimal getCartValue(List<CartItem> list) {
		  BigDecimal total = new BigDecimal(0.0);
		  
		  for(CartItem item: list) {
			  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty()));
			  total= total.add(dprice);
		   }
		  return total;
	  }
	
}
