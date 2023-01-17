package com.merotiffin.category.service;

import com.merotiffin.category.convertor.ProductCategoryConvertor;
import com.merotiffin.category.entity.ProductCategory;
import com.merotiffin.category.model.ProductCategoryPojo;
import com.merotiffin.category.repository.ProductCategoryRepository;
import com.merotiffin.product.convertor.ProductConvertor;
import com.merotiffin.shared.util.GlobalResponse;
import com.merotiffin.shared.util.GlobalUtil;
import jakarta.transaction.Transactional;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService implements IProductCategoryService {

    private final ProductCategoryRepository categoryRepository;
    public static final String PRODUCT_CATEGORY_SAVE_SUCCESS = "";

    @Transactional
    @Override
    public GlobalResponse<List<ProductCategoryPojo>> saveAll(final List<ProductCategoryPojo> productCategories) {
        productCategories.forEach(category -> {
            category.setCreatedDate(LocalDateTime.now());
            category.setCreatedBy("");
        });

        return GlobalUtil
                .globalResponse(
                        String.format(PRODUCT_CATEGORY_SAVE_SUCCESS),
                        HttpStatus.OK,
                        Optional.ofNullable(
                                        categoryRepository.saveAll(ProductCategoryConvertor.toEntityList(productCategories)))
                                .map(ProductCategoryConvertor::toPojoList)
                                .orElse(null),
                        null
                );
    }

    @Override
    public ProductCategory getByName(final String name) {
        return categoryRepository.findByName(name).orElseThrow(() ->
                new RuntimeException(String.format("no product category found by category [%s].", name)));
    }
}
