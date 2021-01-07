package com.stock_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseInvoice is a Querydsl query type for PurchaseInvoice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPurchaseInvoice extends EntityPathBase<PurchaseInvoice> {

    private static final long serialVersionUID = -206103946L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseInvoice purchaseInvoice = new QPurchaseInvoice("purchaseInvoice");

    public final DateTimePath<java.time.LocalDateTime> invoiceDate = createDateTime("invoiceDate", java.time.LocalDateTime.class);

    public final StringPath invoiceNumber = createString("invoiceNumber");

    public final NumberPath<Long> purchaseInvoiceId = createNumber("purchaseInvoiceId", Long.class);

    public final ListPath<PurchaseInvoiceProduct, QPurchaseInvoiceProduct> purchaseInvoiceProducts = this.<PurchaseInvoiceProduct, QPurchaseInvoiceProduct>createList("purchaseInvoiceProducts", PurchaseInvoiceProduct.class, QPurchaseInvoiceProduct.class, PathInits.DIRECT2);

    public final QSupplier supplier;

    public final NumberPath<Double> total = createNumber("total", Double.class);

    public final QUserProfile userProfile;

    public QPurchaseInvoice(String variable) {
        this(PurchaseInvoice.class, forVariable(variable), INITS);
    }

    public QPurchaseInvoice(Path<? extends PurchaseInvoice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseInvoice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseInvoice(PathMetadata metadata, PathInits inits) {
        this(PurchaseInvoice.class, metadata, inits);
    }

    public QPurchaseInvoice(Class<? extends PurchaseInvoice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier")) : null;
        this.userProfile = inits.isInitialized("userProfile") ? new QUserProfile(forProperty("userProfile")) : null;
    }

}

