package com.crs.kttr.product.ticket.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrainTicket is a Querydsl query type for TrainTicket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrainTicket extends EntityPathBase<TrainTicket> {

    private static final long serialVersionUID = -1740097476L;

    public static final QTrainTicket trainTicket = new QTrainTicket("trainTicket");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> issueQuantity = createNumber("issueQuantity", Integer.class);

    public final NumberPath<Integer> maxQuantity = createNumber("maxQuantity", Integer.class);

    public final StringPath name = createString("name");

    public QTrainTicket(String variable) {
        super(TrainTicket.class, forVariable(variable));
    }

    public QTrainTicket(Path<? extends TrainTicket> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrainTicket(PathMetadata metadata) {
        super(TrainTicket.class, metadata);
    }

}

