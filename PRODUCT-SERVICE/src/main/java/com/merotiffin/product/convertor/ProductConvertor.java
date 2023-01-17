package com.merotiffin.product.convertor;

import com.merotiffin.product.entity.Product;
import com.merotiffin.product.model.ProductPojo;
import com.merotiffin.product.model.ProductRequestPojo;

import java.time.LocalDateTime;
import java.util.List;

public class ProductConvertor {

    public static Product toEntity(final ProductPojo source) {
        final Product product = new Product();

        product.setId(source.getId());
        product.setName(source.getName());
        product.setPrice(source.getPrice());
        product.setDescription(source.getDescription());
        product.setSku(source.getSku());
        product.setStock(source.getStock());
        product.setImage(source.getImage());
        product.setCreatedDate(source.getCreatedDate());
        product.setCreatedBy(source.getCreatedBy());
        product.setModifiedDate(source.getModifiedDate());
        product.setModifiedBy(source.getModifiedBy());

        return product;
    }


    public static Product toEntity(final ProductRequestPojo source) {
        final Product product = new Product();

        product.setName(source.getName());
        product.setPrice(source.getPrice());
        product.setDescription(source.getDescription());
        product.setSku(source.getSku());
        product.setStock(source.getStock());
        product.setStock(source.getStock());
        product.setWeight(source.getWeight());
        product.setCreatedBy("");
        product.setCreatedDate(LocalDateTime.now());

        return product;
    }

    public static ProductPojo toPojo(final Product source) {
        final ProductPojo productPojo = new ProductPojo();

        productPojo.setId(source.getId());
        productPojo.setName(source.getName());
        productPojo.setPrice(source.getPrice());
        productPojo.setDescription(source.getDescription());
        productPojo.setSku(source.getSku());
        productPojo.setStock(source.getStock());
        productPojo.setImage(source.getImage());
        productPojo.setCreatedDate(source.getCreatedDate());
        productPojo.setCreatedBy(source.getCreatedBy());
        productPojo.setModifiedDate(source.getModifiedDate());
        productPojo.setModifiedBy(source.getModifiedBy());

        return productPojo;
    }

    public static List<Product> toEntityList(final List<ProductRequestPojo> source) {
        return source.stream().map(ProductConvertor::toEntity).toList();
    }

    public static List<ProductPojo> toPojoList(final List<Product> source) {
        return source.stream().map(ProductConvertor::toPojo).toList();
    }

}
