package com.merotiffin.category.resource;

import com.merotiffin.category.model.ProductCategoryPojo;
import com.merotiffin.category.service.IProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/product_categories"})
@RequiredArgsConstructor
public class ProductCategoryResource {

    private final IProductCategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCategories(@RequestBody final List<ProductCategoryPojo> productCategories) {
        return new ResponseEntity<>(categoryService.saveAll(productCategories), HttpStatus.CREATED);
    }

}
