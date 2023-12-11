package com.taxes.taxe.services;

import com.taxes.taxe.dtos.ProductDto;
import com.taxes.taxe.dtos.TaxDto;
import com.taxes.taxe.enums.TaxType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TaxServiceTest {

    @Autowired
    private TaxService taxService;

    ProductDto productDto = ProductDto.builder()
                                      .build();
    ProductDto productDto1 = ProductDto.builder()
                                       .build();

    @BeforeEach
    private void init() {
        List<TaxDto> taxListTest = Arrays.asList(TaxDto
                                                     .builder()
                                                     .taxType(TaxType.NORMAL.name())
                                                     .name("normal tax")
                                                     .amount(0.1)
                                                     .build(),
            TaxDto
                .builder()
                .taxType(TaxType.IMPORT.name())
                .name("import tax")
                .amount(0.05)
                .build());
        ReflectionTestUtils.setField(taxService, "exemptedTaxProductType", Arrays.asList("BOOK", "FOOD", "MEDICAL"));
        ReflectionTestUtils.setField(taxService, "taxList", taxListTest);

        productDto
            .setPrice(12.49);
        productDto.setImported(false);
        productDto.setProductType("BOOK");
        productDto.setName("book");
        productDto.setQuantity(1);

        productDto1
            .setPrice(27.99);
        productDto1.setImported(true);
        productDto1.setProductType("PERFUME");
        productDto1.setName("imported bottle of perfume");
        productDto1.setQuantity(1);
    }

    @Test
    void calculate_tax_test() {
        ProductDto product = taxService.calculateTax(productDto);
        Assertions.assertEquals(0.0,product.getTaxAmout());

        ProductDto product2 = taxService.calculateTax(productDto1);
        Assertions.assertEquals(4.2, product2.getTaxAmout());
    }
}
