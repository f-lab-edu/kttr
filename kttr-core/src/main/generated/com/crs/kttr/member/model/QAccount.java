package com.crs.kttr.member.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAccount extends BeanPath<Account> {

    private static final long serialVersionUID = -180791644L;

    public static final QAccount account = new QAccount("account");

    public final StringPath signInId = createString("signInId");

    public final StringPath signInPassword = createString("signInPassword");

    public QAccount(String variable) {
        super(Account.class, forVariable(variable));
    }

    public QAccount(Path<? extends Account> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccount(PathMetadata metadata) {
        super(Account.class, metadata);
    }

}

