package com.taxes.taxe.services;

import com.taxes.taxe.dtos.ProductDto;
import com.taxes.taxe.dtos.BillDto;
import com.taxes.taxe.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final TaxService taxService;

    public BillDto calculateBill(List<ProductDto> products) {
        return createBill(products
                              .stream()
                              .map(taxService::calculateTax)
                              .map(this::addTaxToProductPrice)
                              .toList());

    }

    private ProductDto addTaxToProductPrice(ProductDto productDto) {
        productDto
            .setPrice(Utils.roundScaleTwoNumbers(productDto.getQuantity() * productDto.getPrice() + productDto.getTaxAmout()));
        return productDto;
    }

    private BillDto createBill(List<ProductDto> productDtos) {
        return BillDto
                   .builder()
                   .products(productDtos)
                   .saleTax(productDtos
                                .stream()
                                .mapToDouble(ProductDto::getTaxAmout)
                                .sum())
                   .total(Utils.roundScaleTwoNumbers(productDtos
                                                         .stream()
                                                         .mapToDouble(ProductDto::getPrice)
                                                         .sum()))
                   .build();
    }
}
