package com.stock_management.mapper;

import com.stock_management.dto.PurchaseInvoiceDto;
import com.stock_management.dto.PurchaseInvoiceProductDto;
import com.stock_management.dto.SupplierDto;
import com.stock_management.entity.PurchaseInvoice;
import com.stock_management.entity.PurchaseInvoiceProduct;
import com.stock_management.entity.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-08T16:49:27+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class PurchaseInvoiceMapperImpl implements PurchaseInvoiceMapper {

    @Override
    public PurchaseInvoiceDto mapPurchaseInvoiceEntityToDto(PurchaseInvoice purchaseInvoice) {
        if ( purchaseInvoice == null ) {
            return null;
        }

        PurchaseInvoiceDto purchaseInvoiceDto = new PurchaseInvoiceDto();

        purchaseInvoiceDto.setSupplierDto( supplierToSupplierDto( purchaseInvoice.getSupplier() ) );
        purchaseInvoiceDto.setPurchaseInvoiceProductDtos( purchaseInvoiceProductListToPurchaseInvoiceProductDtoList( purchaseInvoice.getPurchaseInvoiceProducts() ) );
        purchaseInvoiceDto.setPurchaseInvoiceId( purchaseInvoice.getPurchaseInvoiceId() );
        purchaseInvoiceDto.setInvoiceNumber( purchaseInvoice.getInvoiceNumber() );
        purchaseInvoiceDto.setInvoiceDate( purchaseInvoice.getInvoiceDate() );
        purchaseInvoiceDto.setTotal( purchaseInvoice.getTotal() );

        return purchaseInvoiceDto;
    }

    @Override
    public PurchaseInvoice mapPurchaseInvoiceDtoToEntity(PurchaseInvoiceDto purchaseInvoiceDto) {
        if ( purchaseInvoiceDto == null ) {
            return null;
        }

        PurchaseInvoice purchaseInvoice = new PurchaseInvoice();

        purchaseInvoice.setPurchaseInvoiceProducts( purchaseInvoiceProductDtoListToPurchaseInvoiceProductList( purchaseInvoiceDto.getPurchaseInvoiceProductDtos() ) );
        purchaseInvoice.setSupplier( supplierDtoToSupplier( purchaseInvoiceDto.getSupplierDto() ) );
        purchaseInvoice.setPurchaseInvoiceId( purchaseInvoiceDto.getPurchaseInvoiceId() );
        purchaseInvoice.setInvoiceNumber( purchaseInvoiceDto.getInvoiceNumber() );
        purchaseInvoice.setInvoiceDate( purchaseInvoiceDto.getInvoiceDate() );
        purchaseInvoice.setTotal( purchaseInvoiceDto.getTotal() );

        return purchaseInvoice;
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

    protected PurchaseInvoiceProductDto purchaseInvoiceProductToPurchaseInvoiceProductDto(PurchaseInvoiceProduct purchaseInvoiceProduct) {
        if ( purchaseInvoiceProduct == null ) {
            return null;
        }

        PurchaseInvoiceProductDto purchaseInvoiceProductDto = new PurchaseInvoiceProductDto();

        purchaseInvoiceProductDto.setPurchaseInvoiceProductId( purchaseInvoiceProduct.getPurchaseInvoiceProductId() );
        purchaseInvoiceProductDto.setOldPricePerBox( purchaseInvoiceProduct.getOldPricePerBox() );
        purchaseInvoiceProductDto.setPricePerBox( purchaseInvoiceProduct.getPricePerBox() );
        purchaseInvoiceProductDto.setBoxesReceived( purchaseInvoiceProduct.getBoxesReceived() );

        return purchaseInvoiceProductDto;
    }

    protected List<PurchaseInvoiceProductDto> purchaseInvoiceProductListToPurchaseInvoiceProductDtoList(List<PurchaseInvoiceProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<PurchaseInvoiceProductDto> list1 = new ArrayList<PurchaseInvoiceProductDto>( list.size() );
        for ( PurchaseInvoiceProduct purchaseInvoiceProduct : list ) {
            list1.add( purchaseInvoiceProductToPurchaseInvoiceProductDto( purchaseInvoiceProduct ) );
        }

        return list1;
    }

    protected PurchaseInvoiceProduct purchaseInvoiceProductDtoToPurchaseInvoiceProduct(PurchaseInvoiceProductDto purchaseInvoiceProductDto) {
        if ( purchaseInvoiceProductDto == null ) {
            return null;
        }

        PurchaseInvoiceProduct purchaseInvoiceProduct = new PurchaseInvoiceProduct();

        purchaseInvoiceProduct.setPurchaseInvoiceProductId( purchaseInvoiceProductDto.getPurchaseInvoiceProductId() );
        purchaseInvoiceProduct.setOldPricePerBox( purchaseInvoiceProductDto.getOldPricePerBox() );
        purchaseInvoiceProduct.setPricePerBox( purchaseInvoiceProductDto.getPricePerBox() );
        purchaseInvoiceProduct.setBoxesReceived( purchaseInvoiceProductDto.getBoxesReceived() );

        return purchaseInvoiceProduct;
    }

    protected List<PurchaseInvoiceProduct> purchaseInvoiceProductDtoListToPurchaseInvoiceProductList(List<PurchaseInvoiceProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<PurchaseInvoiceProduct> list1 = new ArrayList<PurchaseInvoiceProduct>( list.size() );
        for ( PurchaseInvoiceProductDto purchaseInvoiceProductDto : list ) {
            list1.add( purchaseInvoiceProductDtoToPurchaseInvoiceProduct( purchaseInvoiceProductDto ) );
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
        supplier.setEmail( supplierDto.getEmail() );
        supplier.setTelephoneNumber( supplierDto.getTelephoneNumber() );
        supplier.setFax( supplierDto.getFax() );
        supplier.setAddress( supplierDto.getAddress() );

        return supplier;
    }
}
