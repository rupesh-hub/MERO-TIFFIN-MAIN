package com.merotiffin.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.merotiffin.product.model.ProductPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface ProductMapper {

    Page<ProductPojo> productsByCategory(final Page<ProductPojo> productPage,
                                         final Map<String, Object> searchField);

}
