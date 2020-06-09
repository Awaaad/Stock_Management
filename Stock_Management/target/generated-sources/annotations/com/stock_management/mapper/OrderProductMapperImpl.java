package com.stock_management.mapper;

import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderProductDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.entity.Order;
import com.stock_management.entity.OrderProduct;
import com.stock_management.entity.Product;
import com.stock_management.entity.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-09T11:07:47+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
@Component
public class OrderProductMapperImpl implements OrderProductMapper {

    @Override
    public OrderProductDto mapOrderProductEntityToDto(OrderProduct orderProductEntity) {
        if ( orderProductEntity == null ) {
            return null;
        }

        OrderProductDto orderProductDto = new OrderProductDto();

        orderProductDto.setOrderDto( orderToOrderDto( orderProductEntity.getOrder() ) );
        orderProductDto.setProductDto( productToProductDto( orderProductEntity.getProduct() ) );
        orderProductDto.setOrderProductId( orderProductEntity.getOrderProductId() );
        orderProductDto.setProductName( orderProductEntity.getProductName() );
        orderProductDto.setBoxesOrdered( orderProductEntity.getBoxesOrdered() );
        orderProductDto.setUnitsOrdered( orderProductEntity.getUnitsOrdered() );
        orderProductDto.setTotalPrice( orderProductEntity.getTotalPrice() );

        return orderProductDto;
    }

    @Override
    public OrderProduct mapOrderProductDtoToEntity(OrderProductDto orderProductDto) {
        if ( orderProductDto == null ) {
            return null;
        }

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setProduct( productDtoToProduct( orderProductDto.getProductDto() ) );
        orderProduct.setOrder( orderDtoToOrder( orderProductDto.getOrderDto() ) );
        orderProduct.setOrderProductId( orderProductDto.getOrderProductId() );
        orderProduct.setProductName( orderProductDto.getProductName() );
        orderProduct.setBoxesOrdered( orderProductDto.getBoxesOrdered() );
        orderProduct.setUnitsOrdered( orderProductDto.getUnitsOrdered() );
        orderProduct.setTotalPrice( orderProductDto.getTotalPrice() );

        return orderProduct;
    }

    protected OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId( order.getOrderId() );
        orderDto.setCashierName( order.getCashierName() );
        orderDto.setCustomerName( order.getCustomerName() );
        orderDto.setOrderDate( order.getOrderDate() );
        orderDto.setTotalPrice( order.getTotalPrice() );
        orderDto.setPaid( order.getPaid() );

        return orderDto;
    }

    protected SupplierDto supplierToSupplierDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setSupplierId( supplier.getSupplierId() );
        supplierDto.setSupplierName( supplier.getSupplierName() );
        supplierDto.setEmail( supplier.getEmail() );
        supplierDto.setTelephoneNumber( supplier.getTelephoneNumber() );
        supplierDto.setAddress( supplier.getAddress() );

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

    protected Supplier supplierDtoToSupplier(SupplierDto supplierDto) {
        if ( supplierDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setSupplierId( supplierDto.getSupplierId() );
        supplier.setSupplierName( supplierDto.getSupplierName() );
        supplier.setEmail( supplierDto.getEmail() );
        supplier.setTelephoneNumber( supplierDto.getTelephoneNumber() );
        supplier.setAddress( supplierDto.getAddress() );

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

    protected Order orderDtoToOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderId( orderDto.getOrderId() );
        order.setCashierName( orderDto.getCashierName() );
        order.setCustomerName( orderDto.getCustomerName() );
        order.setOrderDate( orderDto.getOrderDate() );
        order.setTotalPrice( orderDto.getTotalPrice() );
        order.setPaid( orderDto.getPaid() );

        return order;
    }
}
