package com.taxes.taxe.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BillDto {

    private List<ProductDto> products;
    private Double saleTax;
    private Double total;
}
