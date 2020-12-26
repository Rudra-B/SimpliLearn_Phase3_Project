package com.ecommerce.SportyShoes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.SportyShoes.entity.Category;

public interface CategoryDAO extends CrudRepository<Category, Integer>{

	@Query(value="select * from Category where id= :id", nativeQuery = true)
	public Category getCategoryById(@Param("id") int id);
	
	
	@Query(value="select * from category", nativeQuery = true)
	public List<Category> getAllCategory();
}
