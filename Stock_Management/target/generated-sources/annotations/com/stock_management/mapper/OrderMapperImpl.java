package com.stock_management.mapper;

import com.stock_management.dto.OrderDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.entity.Order;
import com.stock_management.entity.Product;
import com.stock_management.entity.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-29T21:38:41+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto mapOrderEntityToDto(Order orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId( orderEntity.getOrderId() );
        orderDto.setCashierName( orderEntity.getCashierName() );
        orderDto.setCustomerName( orderEntity.getCustomerName() );
        orderDto.setOrderDate( orderEntity.getOrderDate() );
        orderDto.setProductName( orderEntity.getProductName() );
        orderDto.setQuantityOrderedBox( orderEntity.getQuantityOrderedBox() );
        orderDto.setQuantityOrderedUnit( orderEntity.getQuantityOrderedUnit() );
        orderDto.setProducts( productListToProductDtoList( orderEntity.getProducts() ) );
        orderDto.setTotalPrice( orderEntity.getTotalPrice() );

        return orderDto;
    }

    @Override
    public Order mapOrderDtoToEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderId( orderDto.getOrderId() );
        order.setCashierName( orderDto.getCashierName() );
        order.setCustomerName( orderDto.getCustomerName() );
        order.setOrderDate( orderDto.getOrderDate() );
        order.setProductName( orderDto.getProductName() );
        order.setQuantityOrderedBox( orderDto.getQuantityOrderedBox() );
        order.setQuantityOrderedUnit( orderDto.getQuantityOrderedUnit() );
        order.setTotalPrice( orderDto.getTotalPrice() );
        order.setProducts( productDtoListToProductList( orderDto.getProducts() ) );

        return order;
    }

    protected SupplierDto supplierToSupplierDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setSupplierId( supplier.getSupplierId() );
        supplierDto.setSupplierName( supplier.getSupplierName() );

        return supplierDto;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setProductId( product.getProductId() );
        productDto.setProductName( product.getProductName() );
        productDto.setDescription( product.getDescription() );
        productDto.setDosage( product.getDosage() );
        productDto.setCategory( product.getCategory() );
        productDto.setBox( product.getBox() );
        productDto.setUnitsPerBox( product.getUnitsPerBox() );
        productDto.setUnitsTotal( product.getUnitsTotal() );
        productDto.setPricePerBox( product.getPricePerBox() );
        productDto.setPricePerUnit( product.getPricePerUnit() );
        productDto.setRequirePrescription( product.getRequirePrescription() );
        productDto.setSupplier( supplierToSupplierDto( product.getSupplier() ) );

        return productDto;
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }

    protected Supplier supplierDtoToSupplier(SupplierDto supplierDto) {
        if ( supplierDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setSupplierId( supplierDto.getSupplierId() );
        supplier.setSupplierName( supplierDto.getSupplierName() );

        return supplier;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( productDto.getProductId() );
        product.setProductName( productDto.getProductName() );
        product.setDescription( productDto.getDescription() );
        product.setDosage( productDto.getDosage() );
        product.setCategory( productDto.getCategory() );
        product.setBox( productDto.getBox() );
        product.setUnitsPerBox( productDto.getUnitsPerBox() );
        product.setUnitsTotal( productDto.getUnitsTotal() );
        product.setPricePerBox( productDto.getPricePerBox() );
        product.setPricePerUnit( productDto.getPricePerUnit() );
        product.setRequirePrescription( productDto.getRequirePrescription() );
        product.setSupplier( supplierDtoToSupplier( productDto.getSupplier() ) );

        return product;
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productDtoToProduct( productDto ) );
        }

        return list1;
    }
}
