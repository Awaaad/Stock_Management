package com.stock_management.mapper;

import com.stock_management.dto.ProductDto;
import com.stock_management.dto.PurchaseInvoiceProductDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.PurchaseInvoiceProduct;
import com.stock_management.entity.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-08T14:32:36+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class PurchaseInvoiceProductMapperImpl implements PurchaseInvoiceProductMapper {

    @Override
    public PurchaseInvoiceProductDto mapPurchaseInvoiceProductEntityToDto(PurchaseInvoiceProduct purchaseInvoiceProduct) {
        if ( purchaseInvoiceProduct == null ) {
            return null;
        }

        PurchaseInvoiceProductDto purchaseInvoiceProductDto = new PurchaseInvoiceProductDto();

        purchaseInvoiceProductDto.setProductDto( productToProductDto( purchaseInvoiceProduct.getProduct() ) );
        purchaseInvoiceProductDto.setPurchaseInvoiceProductId( purchaseInvoiceProduct.getPurchaseInvoiceProductId() );
        purchaseInvoiceProductDto.setOldPricePerBox( purchaseInvoiceProduct.getOldPricePerBox() );
        purchaseInvoiceProductDto.setPricePerBox( purchaseInvoiceProduct.getPricePerBox() );
        purchaseInvoiceProductDto.setBoxesReceived( purchaseInvoiceProduct.getBoxesReceived() );

        return purchaseInvoiceProductDto;
    }

    @Override
    public PurchaseInvoiceProduct mapPurchaseInvoiceProductDtoToEntity(PurchaseInvoiceProductDto purchaseInvoiceProductDto) {
        if ( purchaseInvoiceProductDto == null ) {
            return null;
        }

        PurchaseInvoiceProduct purchaseInvoiceProduct = new PurchaseInvoiceProduct();

        purchaseInvoiceProduct.setProduct( productDtoToProduct( purchaseInvoiceProductDto.getProductDto() ) );
        purchaseInvoiceProduct.setPurchaseInvoiceProductId( purchaseInvoiceProductDto.getPurchaseInvoiceProductId() );
        purchaseInvoiceProduct.setOldPricePerBox( purchaseInvoiceProductDto.getOldPricePerBox() );
        purchaseInvoiceProduct.setPricePerBox( purchaseInvoiceProductDto.getPricePerBox() );
        purchaseInvoiceProduct.setBoxesReceived( purchaseInvoiceProductDto.getBoxesReceived() );

        return purchaseInvoiceProduct;
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
        supplierDto.setFax( supplier.getFax() );
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
        productDto.setMinStockAmount( product.getMinStockAmount() );
        productDto.setUnitsPerBox( product.getUnitsPerBox() );
        productDto.setUnitsTotal( product.getUnitsTotal() );
        productDto.setOldPricePerBox( product.getOldPricePerBox() );
        productDto.setPricePerBox( product.getPricePerBox() );
        productDto.setPricePerUnit( product.getPricePerUnit() );
        productDto.setRequirePrescription( product.getRequirePrescription() );
        productDto.setSlot( product.getSlot() );
        productDto.setExpiryDate( product.getExpiryDate() );
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
        supplier.setFax( supplierDto.getFax() );
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
        product.setMinStockAmount( productDto.getMinStockAmount() );
        product.setUnitsPerBox( productDto.getUnitsPerBox() );
        product.setUnitsTotal( productDto.getUnitsTotal() );
        product.setOldPricePerBox( productDto.getOldPricePerBox() );
        product.setPricePerBox( productDto.getPricePerBox() );
        product.setPricePerUnit( productDto.getPricePerUnit() );
        product.setRequirePrescription( productDto.getRequirePrescription() );
        product.setSlot( productDto.getSlot() );
        product.setExpiryDate( productDto.getExpiryDate() );
        product.setSupplier( supplierDtoToSupplier( productDto.getSupplier() ) );

        return product;
    }
}
