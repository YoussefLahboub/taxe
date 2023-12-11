package com.taxes.taxe.controllers;

import com.taxes.taxe.dtos.TaxDto;
import com.taxes.taxe.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/add")
    void addTax(@RequestBody TaxDto taxDto)  {
        taxService.addTax(taxDto);
    }

    @GetMapping("/findAll")
    List<TaxDto> findAll()  {
        return taxService.findAll();
    }
}
