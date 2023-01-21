package com.merotiffin.product.resource;

import com.merotiffin.product.model.ProductRequestPojo;
import com.merotiffin.product.service.IProductService;
import com.merotiffin.shared.model.PagingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = {"/products"})
public class ProductResource {

    private final IProductService productService;

    @PostMapping(path = {"/save-all"})
    public ResponseEntity<?> test(@RequestBody final Map<String, List<ProductRequestPojo>> products) {
        return new ResponseEntity(productService.saveAll(products), HttpStatus.CREATED);
    }

    @PostMapping(path = {"/paginated"})
    public ResponseEntity<?> allProductsByCategory(@RequestBody final PagingRequest pagingRequest) {
        return new ResponseEntity(productService.allProductsByCategory(pagingRequest), HttpStatus.OK);
    }

}
