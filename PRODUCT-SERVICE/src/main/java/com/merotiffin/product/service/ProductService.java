package com.merotiffin.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.merotiffin.category.entity.ProductCategory;
import com.merotiffin.category.service.ProductCategoryService;
import com.merotiffin.product.convertor.ProductConvertor;
import com.merotiffin.product.entity.Product;
import com.merotiffin.product.mapper.ProductMapper;
import com.merotiffin.product.model.ProductPojo;
import com.merotiffin.product.model.ProductRequestPojo;
import com.merotiffin.product.repository.ProductRepository;
import com.merotiffin.shared.model.Pagination;
import com.merotiffin.shared.model.PagingRequest;
import com.merotiffin.shared.util.GlobalResponse;
import com.merotiffin.shared.util.GlobalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductCategoryService categoryService;
    private static final String PRODUCT_SAVE_SUCCESS = "";

    @Override
    public GlobalResponse<Map<String, List<ProductRequestPojo>>> saveAll(final Map<String, List<ProductRequestPojo>> dataList) {

        dataList.forEach((category, productList) -> {
            final List<Product> products = ProductConvertor.toEntityList(productList);
            final ProductCategory productCategory = categoryService.getByName(category);
            productCategory.setProducts(products);

            productRepository.saveAll(products);
        });

        return GlobalUtil
                .globalResponse(
                        String.format(PRODUCT_SAVE_SUCCESS),
                        HttpStatus.OK,
                        dataList,
                        null
                );
    }

    @Override
    public GlobalResponse<List<ProductPojo>> allProductsByCategory(final PagingRequest pagingRequest) {

        final Page productResponsePage = productMapper.productsByCategory(new Page(pagingRequest.getPage(),
                pagingRequest.getLimit()),
                pagingRequest.getSearchField());

        return GlobalUtil
                .globalResponse(
                        String.format(""),
                        HttpStatus.OK,
                        productResponsePage.getRecords(),
                        Pagination
                                .builder()
                                .page(productResponsePage.getCurrent())
                                .pageSize(productResponsePage.getSize())
                                .totalPages(productResponsePage.getPages())
                                .totalRecords(productResponsePage.getTotal())
                                .build()
                );
    }

}
