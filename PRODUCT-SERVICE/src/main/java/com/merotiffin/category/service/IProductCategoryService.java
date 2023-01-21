package com.merotiffin.category.service;

import com.merotiffin.category.entity.ProductCategory;
import com.merotiffin.category.model.ProductCategoryPojo;
import com.merotiffin.shared.util.GlobalResponse;

import java.util.List;

public interface IProductCategoryService {

    GlobalResponse<List<ProductCategoryPojo>> saveAll(final List<ProductCategoryPojo> productCategories);
    ProductCategory getByName(final String name);

}
