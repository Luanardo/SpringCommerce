package com.springcommerce.midterm.dto;

import com.springcommerce.midterm.model.Order;
import com.springcommerce.midterm.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    private Long id;
    private Long order;
    private Long product;
    private int quantity;
}
