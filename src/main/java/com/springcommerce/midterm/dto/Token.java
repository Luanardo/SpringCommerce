package com.springcommerce.midterm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Token implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String token;
}
