package co.kr.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import co.kr.community.entity.Category;

@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
