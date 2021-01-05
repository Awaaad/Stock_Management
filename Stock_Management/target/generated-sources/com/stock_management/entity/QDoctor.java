package com.stock_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDoctor is a Querydsl query type for Doctor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDoctor extends EntityPathBase<Doctor> {

    private static final long serialVersionUID = -924101035L;

    public static final QDoctor doctor = new QDoctor("doctor");

    public final StringPath address = createString("address");

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final NumberPath<Integer> telephoneNumber = createNumber("telephoneNumber", Integer.class);

    public QDoctor(String variable) {
        super(Doctor.class, forVariable(variable));
    }

    public QDoctor(Path<? extends Doctor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDoctor(PathMetadata metadata) {
        super(Doctor.class, metadata);
    }

}

