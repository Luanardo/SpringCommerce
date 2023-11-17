package com.springcommerce.midterm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailResDTO {
    private Long id;
    private Long order;
    private String productName;
    private double productPrice;
    private int quantity;
}
