package com.stock_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDoctor is a Querydsl query type for Doctor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDoctor extends EntityPathBase<Doctor> {

    private static final long serialVersionUID = -924101035L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDoctor doctor = new QDoctor("doctor");

    public final StringPath address = createString("address");

    public final QUserProfile createdBy;

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath firstName = createString("firstName");

    public final QUserProfile lastModifiedBy;

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final StringPath lastName = createString("lastName");

    public final NumberPath<Integer> telephoneNumber = createNumber("telephoneNumber", Integer.class);

    public QDoctor(String variable) {
        this(Doctor.class, forVariable(variable), INITS);
    }

    public QDoctor(Path<? extends Doctor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDoctor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDoctor(PathMetadata metadata, PathInits inits) {
        this(Doctor.class, metadata, inits);
    }

    public QDoctor(Class<? extends Doctor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUserProfile(forProperty("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUserProfile(forProperty("lastModifiedBy")) : null;
    }

}

