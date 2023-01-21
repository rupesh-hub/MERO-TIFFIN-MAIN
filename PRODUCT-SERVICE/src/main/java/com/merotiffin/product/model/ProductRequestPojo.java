package com.merotiffin.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestPojo {

    private String sku;
    private String name;
    private Double price;
    private long weight;
    private String description;
    private String image;
    private int stock;

}
