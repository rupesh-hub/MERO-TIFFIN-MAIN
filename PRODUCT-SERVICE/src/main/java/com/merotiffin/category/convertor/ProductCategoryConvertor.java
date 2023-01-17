package com.merotiffin.category.convertor;

import com.merotiffin.category.entity.ProductCategory;
import com.merotiffin.category.model.ProductCategoryPojo;

import java.util.List;

public class ProductCategoryConvertor {

    public static ProductCategory toEntity(final ProductCategoryPojo source) {
        final ProductCategory productCategory = new ProductCategory();
        productCategory.setName(source.getName());
        productCategory.setDescription(source.getDescription());
        productCategory.setCreatedBy(source.getCreatedBy());
        productCategory.setCreatedDate(source.getCreatedDate());
        productCategory.setModifiedBy(source.getModifiedBy());
        productCategory.setModifiedDate(source.getModifiedDate());
        return productCategory;
    }

    public static ProductCategoryPojo toPojo(final ProductCategory source) {
        final ProductCategoryPojo productCategoryPojo = new ProductCategoryPojo();
        productCategoryPojo.setName(source.getName());
        productCategoryPojo.setDescription(source.getDescription());
        productCategoryPojo.setCreatedBy(source.getCreatedBy());
        productCategoryPojo.setCreatedDate(source.getCreatedDate());
        productCategoryPojo.setModifiedBy(source.getModifiedBy());
        productCategoryPojo.setModifiedDate(source.getModifiedDate());
        return productCategoryPojo;
    }

    public static List<ProductCategory> toEntityList(List<ProductCategoryPojo> source) {
        return source.stream().map(ProductCategoryConvertor::toEntity).toList();
    }

    public static List<ProductCategoryPojo> toPojoList(List<ProductCategory> source) {
        return source.stream().map(ProductCategoryConvertor::toPojo).toList();
    }
}
