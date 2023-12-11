package com.taxes.taxe.config;

import com.taxes.taxe.dtos.TaxDto;
import com.taxes.taxe.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.taxes.taxe.enums.TaxType.IMPORT;
import static com.taxes.taxe.enums.TaxType.NORMAL;

@Configuration
public class TaxConfig {

    private final TaxService taxService;

    @Autowired
    public TaxConfig(TaxService taxService) {
        this.taxService = taxService;
    }

    @Bean
    public List<TaxDto> taxList(){
        TaxDto normalTax = TaxDto
                               .builder()
                               .name("normal tax")
                               .amount(0.1)
                               .taxType(NORMAL.name())
                               .build();
        TaxDto importTax = TaxDto
                               .builder()
                               .name("import tax")
                               .amount(0.05)
                               .taxType(IMPORT.name())
                               .build();
        taxService.addTax(normalTax);
        taxService.addTax(importTax);
        return taxService.findAll();
    }
}
