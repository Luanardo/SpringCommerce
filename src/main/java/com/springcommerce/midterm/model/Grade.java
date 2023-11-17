package com.springcommerce.midterm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Grade {
    @Id
    private String id;

    private String name;

    private String ratio;

    @JsonIgnore
    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    private List<Product> products;
}
