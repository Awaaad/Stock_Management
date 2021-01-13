package com.stock_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -720720263L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Double> box = createNumber("box", Double.class);

    public final StringPath category = createString("category");

    public final StringPath description = createString("description");

    public final StringPath dosage = createString("dosage");

    public final DatePath<java.time.LocalDate> expiryDate = createDate("expiryDate", java.time.LocalDate.class);

    public final NumberPath<Integer> minStockAmount = createNumber("minStockAmount", Integer.class);

    public final NumberPath<Double> oldPricePerBox = createNumber("oldPricePerBox", Double.class);

    public final ListPath<OrderProduct, QOrderProduct> orderProducts = this.<OrderProduct, QOrderProduct>createList("orderProducts", OrderProduct.class, QOrderProduct.class, PathInits.DIRECT2);

    public final NumberPath<Double> pricePerBox = createNumber("pricePerBox", Double.class);

    public final NumberPath<Double> pricePerUnit = createNumber("pricePerUnit", Double.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productName = createString("productName");

    public final ListPath<PurchaseInvoiceProduct, QPurchaseInvoiceProduct> purchaseInvoiceProducts = this.<PurchaseInvoiceProduct, QPurchaseInvoiceProduct>createList("purchaseInvoiceProducts", PurchaseInvoiceProduct.class, QPurchaseInvoiceProduct.class, PathInits.DIRECT2);

    public final BooleanPath requirePrescription = createBoolean("requirePrescription");

    public final StringPath slot = createString("slot");

    public final QSupplier supplier;

    public final NumberPath<Integer> unitsPerBox = createNumber("unitsPerBox", Integer.class);

    public final NumberPath<Double> unitsTotal = createNumber("unitsTotal", Double.class);

    public final NumberPath<Double> wholeSalePrice = createNumber("wholeSalePrice", Double.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier")) : null;
    }

}

