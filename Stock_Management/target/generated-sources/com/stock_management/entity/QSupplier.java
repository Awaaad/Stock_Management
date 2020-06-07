package com.stock_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSupplier is a Querydsl query type for Supplier
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSupplier extends EntityPathBase<Supplier> {

    private static final long serialVersionUID = -1527035518L;

    public static final QSupplier supplier = new QSupplier("supplier");

    public final StringPath Address = createString("Address");

    public final StringPath email = createString("email");

    public final NumberPath<Long> supplierId = createNumber("supplierId", Long.class);

    public final StringPath supplierName = createString("supplierName");

    public final NumberPath<Integer> telephoneNumber = createNumber("telephoneNumber", Integer.class);

    public QSupplier(String variable) {
        super(Supplier.class, forVariable(variable));
    }

    public QSupplier(Path<? extends Supplier> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSupplier(PathMetadata metadata) {
        super(Supplier.class, metadata);
    }

}

