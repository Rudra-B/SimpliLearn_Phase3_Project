package com.ecommerce.SportyShoes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.SportyShoes.dao.ProductDAO;
import com.ecommerce.SportyShoes.entity.Product;

@Controller
public class Test {

	@Autowired
	ProductDAO dao;
	
	
	/*
	 * @RequestMapping("/") //@ResponseBody public String display(Model model) {
	 * String abc=null; abc.length(); Product product=dao.getProduct(2);
	 * 
	 * List<Product> list=dao.getAllProducts();
	 * 
	 * for(Product pr: list) { System.out.println(pr); }
	 * 
	 * System.out.println(product);
	 * 
	 * model.addAttribute("list", list); model.addAttribute("pageTitle",
	 * "SPORTY SHOES - HOMEPAGE");
	 * 
	 * 
	 * return "index"; }
	 */
	
	/*
	 * @ExceptionHandler(value=NullPointerException.class) public String
	 * nullpointerexception(Model model) {
	 * 
	 * model.addAttribute("error", "NullPointer Exception Occured");
	 * 
	 * return "exception"; }
	 */

}
