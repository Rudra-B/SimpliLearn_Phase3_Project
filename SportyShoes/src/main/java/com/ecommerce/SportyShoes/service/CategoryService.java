package com.ecommerce.SportyShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.SportyShoes.dao.CategoryDAO;
import com.ecommerce.SportyShoes.entity.Category;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDAO categoryDAO;
	public Category getCategoryById(int id)
	{
		
		return categoryDAO.getCategoryById(id);
	}
	
	
	public List<Category> getAllCategories() {
		
		return categoryDAO.getAllCategory();
	}


	public void deleteCategory(int idValue) {

		categoryDAO.deleteById(idValue);
		
	}


	public void updateCategory(Category category) {
		 
		categoryDAO.save(category);
		
	}
	
	
	@Transactional
	 public String getCategoriesDropDown(int id) {
		 StringBuilder sb = new StringBuilder("");
		 List<Category> list = categoryDAO.getAllCategory();
		 for(Category cat: list) {
			 if (cat.getId() == id)
				 sb.append("<option value=" + String.valueOf(cat.getId()) + " selected>" + cat.getName() + "</option>");
			 else
				 sb.append("<option value=" + String.valueOf(cat.getId()) + ">" + cat.getName() + "</option>");
				 
		 }
		 return sb.toString();
		}

}
