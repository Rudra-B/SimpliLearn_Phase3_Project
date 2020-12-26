package com.ecommerce.SportyShoes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.SportyShoes.entity.Product;


public interface ProductDAO extends CrudRepository<Product, Integer> {
	
	@Query(value="select * from Product where id = ?1", nativeQuery = true)
	public Product getProductById(long id);
	//public Product getProductById(@Param("id") int id);
	
	
	@Query(value="select * from Product", nativeQuery = true)
	public List<Product> getAllProduct();

}
