package com.stock_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseInvoiceProduct is a Querydsl query type for PurchaseInvoiceProduct
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPurchaseInvoiceProduct extends EntityPathBase<PurchaseInvoiceProduct> {

    private static final long serialVersionUID = -779568743L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseInvoiceProduct purchaseInvoiceProduct = new QPurchaseInvoiceProduct("purchaseInvoiceProduct");

    public final NumberPath<Integer> boxesReceived = createNumber("boxesReceived", Integer.class);

    public final NumberPath<Double> oldPricePerBox = createNumber("oldPricePerBox", Double.class);

    public final NumberPath<Double> pricePerBox = createNumber("pricePerBox", Double.class);

    public final QProduct product;

    public final QPurchaseInvoice purchaseInvoice;

    public final NumberPath<Long> purchaseInvoiceProductId = createNumber("purchaseInvoiceProductId", Long.class);

    public QPurchaseInvoiceProduct(String variable) {
        this(PurchaseInvoiceProduct.class, forVariable(variable), INITS);
    }

    public QPurchaseInvoiceProduct(Path<? extends PurchaseInvoiceProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseInvoiceProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseInvoiceProduct(PathMetadata metadata, PathInits inits) {
        this(PurchaseInvoiceProduct.class, metadata, inits);
    }

    public QPurchaseInvoiceProduct(Class<? extends PurchaseInvoiceProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.purchaseInvoice = inits.isInitialized("purchaseInvoice") ? new QPurchaseInvoice(forProperty("purchaseInvoice"), inits.get("purchaseInvoice")) : null;
    }

}

