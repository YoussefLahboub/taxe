package com.taxes.taxe.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDto {
    private String name;
    private Double price;
    private int quantity;
    private String productType;
    private boolean isImported;
    private double taxAmout;
}
