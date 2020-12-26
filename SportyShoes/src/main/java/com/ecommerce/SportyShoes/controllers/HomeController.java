package com.ecommerce.SportyShoes.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ecommerce.SportyShoes.entity.Category;
import com.ecommerce.SportyShoes.entity.Product;
import com.ecommerce.SportyShoes.service.CategoryService;
import com.ecommerce.SportyShoes.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = { "/", "/home" })
	public String home(Model model) {

		List<Product> list = productService.getAllProduct();
		Map<Integer, String> catMap = new HashMap();

		for (Product product : list) {
			Category category = categoryService.getCategoryById(product.getCategory_id());
			if (category != null) {
				catMap.put(product.getId(), category.getName());
			}

		}

		model.addAttribute("list", list);
		model.addAttribute("catmap", catMap);
		model.addAttribute("pageTitle", "SPORTY SHOES - HOMEPAGE");

		return "index";
	}

}
