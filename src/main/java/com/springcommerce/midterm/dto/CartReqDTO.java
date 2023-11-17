package com.springcommerce.midterm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartReqDTO {
    private Long id;
    private String customer;
    private Long product;
    private int quantity;
}
