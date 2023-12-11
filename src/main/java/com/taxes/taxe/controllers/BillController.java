package com.taxes.taxe.controllers;

import com.taxes.taxe.dtos.ProductDto;
import com.taxes.taxe.dtos.BillDto;
import com.taxes.taxe.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;

    @GetMapping("/calculateBill")
    BillDto calculateBill(@RequestBody List<ProductDto> productDtos) {
        return billService.calculateBill(productDtos);
    }
}
