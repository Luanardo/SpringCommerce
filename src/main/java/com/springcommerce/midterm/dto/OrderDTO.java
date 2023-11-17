package com.springcommerce.midterm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private String customer;
    private String phoneNumber;
    private String address;
    private Date createdAt;
    private double total;
    private String status;
}
