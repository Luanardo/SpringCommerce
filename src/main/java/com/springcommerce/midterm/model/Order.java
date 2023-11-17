package com.springcommerce.midterm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    private String phoneNumber;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private double total;

    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        status = "Chờ xác nhận";
    }
}
