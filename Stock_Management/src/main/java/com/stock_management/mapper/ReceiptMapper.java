package com.stock_management.mapper;

import com.querydsl.core.Tuple;
import com.stock_management.dto.CustomerReceiptDto;
import com.stock_management.dto.ProductQuantityPriceDto;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {


    default CustomerReceiptDto mapCustomerReceiptDtoEntityToDto(List<Tuple> tupleList) {
        if (CollectionUtils.isEmpty(tupleList)) {
            return null;
        }

        var tuple = tupleList.get(0);
        CustomerReceiptDto customerReceiptDto = new CustomerReceiptDto();
        customerReceiptDto.setOrderId(tuple.get(0, Long.class));
        customerReceiptDto.setCashierName(tuple.get(1, String.class));
        customerReceiptDto.setCustomerName(tuple.get(2, String.class));
        customerReceiptDto.setOrderDate(tuple.get(3, LocalDateTime.class));
        customerReceiptDto.setTotalPrice(tuple.get(4, Double.class));

        var productQuantity = new ArrayList<ProductQuantityPriceDto>();
        tupleList.forEach(tupleData -> {
            ProductQuantityPriceDto productQuantityPriceDto = new ProductQuantityPriceDto();
            productQuantityPriceDto.setProductName(tupleData.get(5, String.class));
            productQuantityPriceDto.setBoxesOrdered(tupleData.get(6, Integer.class));
            productQuantityPriceDto.setUnitsOrdered(tupleData.get(7, Integer.class));
            productQuantityPriceDto.setPrice(tupleData.get(8, Double.class));
            productQuantityPriceDto.setPricePerBox(tupleData.get(9, Double.class));
            productQuantityPriceDto.setPricePerUnit(tupleData.get(10, Double.class));
            productQuantityPriceDto.setUnitsPerBox(tupleData.get(11, Integer.class));

            productQuantity.add(productQuantityPriceDto);

        });

        customerReceiptDto.setProductQuantityPriceDto(productQuantity);

        return customerReceiptDto;
    }
}
