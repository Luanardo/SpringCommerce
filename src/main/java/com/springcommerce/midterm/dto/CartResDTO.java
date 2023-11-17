package com.springcommerce.midterm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartResDTO {
    private Long id;
    private Long customer;
    private Long productId;
    private String productImage;
    private String productName;
    private double productPrice;
    private int quantity;
}
