package com.stock_management.mapper;

import com.stock_management.dto.SaveMultipleProductsDto;
import com.stock_management.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface MultipleSaveProductMapper {

    @Mapping(target = "product.productId", source = "saveMultipleProductsDto.productDto.productId")
    @Mapping(target = "product.productName", source = "saveMultipleProductsDto.productDto.productName")
    @Mapping(target = "product.description", source = "saveMultipleProductsDto.productDto.description")
    @Mapping(target = "product.dosage", source = "saveMultipleProductsDto.productDto.dosage")
    @Mapping(target = "product.cateory", source = "saveMultipleProductsDto.productDto.cateory")
    @Mapping(target = "product.box", source = "saveMultipleProductsDto.productDto.box")
    @Mapping(target = "product.unitsPerBox", source = "saveMultipleProductsDto.productDto.unitsPerBox")
    @Mapping(target = "product.unitsTotal", source = "saveMultipleProductsDto.productDto.unitsTotal")
    @Mapping(target = "product.pricePerBox", source = "saveMultipleProductsDto.productDto.pricePerBox")
    @Mapping(target = "product.pricePerUnit", source = "saveMultipleProductsDto.productDto.pricePerUnit")
    @Mapping(target = "product.requirePrescription", source = "saveMultipleProductsDto.productDto.requirePrescription")
    @Mapping(target = "product.supplier", source = "saveMultipleProductsDto.productDto.supplier")
    Product mapSaveMultipleProductsDtoToEntity (SaveMultipleProductsDto saveMultipleProductsDto);
}
