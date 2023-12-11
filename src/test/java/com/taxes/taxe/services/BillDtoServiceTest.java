package com.taxes.taxe.services;

import com.taxes.taxe.dtos.ProductDto;
import com.taxes.taxe.dtos.BillDto;
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
class BillDtoServiceTest {
    @Autowired
    private TaxService taxService;
    @Autowired
    private BillService billService;

    ProductDto productDto1 = ProductDto.builder()
                                       .build();
    ProductDto productDto2 = ProductDto.builder()
                                       .build();
    ProductDto productDto3 = ProductDto.builder()
                                       .build();

    @BeforeEach
    private void init() {
        ReflectionTestUtils.setField(taxService, "exemptedTaxProductType", Arrays.asList("BOOK", "FOOD", "MEDICAL"));
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
        ReflectionTestUtils.setField(taxService, "taxList", taxListTest);

        productDto1
            .setPrice(12.49);
        productDto1.setImported(false);
        productDto1.setProductType("BOOK");
        productDto1.setName("book");
        productDto1.setQuantity(1);

        productDto2
            .setPrice(14.99);
        productDto2.setImported(false);
        productDto2.setProductType("ART");
        productDto2.setName("music CD");
        productDto2.setQuantity(1);

        productDto3
            .setPrice(0.85);
        productDto3.setImported(false);
        productDto3.setProductType("FOOD");
        productDto3.setName("chocolate bar");
        productDto3.setQuantity(1);
    }

    @Test
    void create_bill_Test() {
        BillDto billDto = billService.calculateBill(Arrays.asList(productDto1, productDto2, productDto3));
        Assertions.assertEquals(1.5, billDto.getSaleTax());
        Assertions.assertEquals(29.83, billDto.getTotal());
    }
}
