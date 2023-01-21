package com.merotiffin.category.repository;

import com.merotiffin.category.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query(nativeQuery = true, value = "select *  from product_category pc where lower(pc.name)=lower(?1)")
    Optional<ProductCategory> findByName(String name);

}
