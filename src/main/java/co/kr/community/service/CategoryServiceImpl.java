package co.kr.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.community.entity.Category;
import co.kr.community.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> getCategoryList() {
		
		List<Category> categories = categoryRepository.findAll();
		System.out.println("service impl 내 categories >>>> " + categories);
		return categories;
	}
	
	
	
}
