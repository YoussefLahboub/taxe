package com.taxes.taxe.services;

import com.taxes.taxe.Exceptions.TaxNotFoundException;
import com.taxes.taxe.dtos.ProductDto;
import com.taxes.taxe.dtos.TaxDto;
import com.taxes.taxe.enums.TaxType;
import com.taxes.taxe.models.Tax;
import com.taxes.taxe.repositories.TaxRepository;
import com.taxes.taxe.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxService {

    @Value("${exempted.tax.productType}")
    private List<String> exemptedTaxProductType;
    private final TaxRepository taxRepository;
    private final ModelMapper modelMapper;
    private final List<TaxDto> taxList;

    public TaxService(TaxRepository taxRepository, ModelMapper modelMapper, @Lazy List<TaxDto> taxList) {
        this.taxRepository = taxRepository;
        this.modelMapper = modelMapper;
        this.taxList = taxList;
    }

    public ProductDto calculateTax(ProductDto product) {

        if (!exemptedTaxProductType.contains(product.getProductType())) {
            product
                .setTaxAmout(Utils.roundScaleTwoNumbers(product.getTaxAmout() + (product.getQuantity() * product.getPrice() * getTaxByType(TaxType.NORMAL.name()))));
        }
        if (product.isImported()) {
            product
                .setTaxAmout(Utils.roundScaleTwoNumbers(product.getTaxAmout() + (product.getQuantity() * product.getPrice() * getTaxByType(TaxType.IMPORT.name()))));
        }
        return product;
    }

    public void addTax(TaxDto taxDto) {
        Tax tax = new ModelMapper().map(taxDto, Tax.class);
        taxRepository.save(tax);
    }

    public List<TaxDto> findAll() {
        return taxRepository.findAll()
                            .stream()
                            .map(tax -> modelMapper.map(tax, TaxDto.class))
                            .toList();
    }

    private Double getTaxByType(String type) {
        return taxList.stream()
                      .filter(taxDto -> taxDto.getTaxType()
                                              .equals(type))
                      .findFirst()
                      .orElseThrow(() -> new TaxNotFoundException("Tax not Found"))
                      .getAmount();
    }
}
