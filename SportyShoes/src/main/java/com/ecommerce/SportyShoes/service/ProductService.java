package com.ecommerce.SportyShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.SportyShoes.dao.ProductDAO;
import com.ecommerce.SportyShoes.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO productDAO;
	
	public Product getProductById(long id)
	{
	
		return productDAO.getProductById(id);
	}
	
	public List<Product> getAllProduct()
	{
		
		return productDAO.getAllProduct();
	}

	public void deleteProduct(int idValue) {
		
		
		productDAO.deleteById(idValue);
		
	}

	public void updateProduct(Product product) {
		
		productDAO.save(product);
		
	}

	

}
