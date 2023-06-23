package com.crs.kttr.reservation.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservationDetails is a Querydsl query type for ReservationDetails
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationDetails extends EntityPathBase<ReservationDetails> {

    private static final long serialVersionUID = 974615149L;

    public static final QReservationDetails reservationDetails = new QReservationDetails("reservationDetails");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath reservationCode = createString("reservationCode");

    public final NumberPath<Long> ticketId = createNumber("ticketId", Long.class);

    public QReservationDetails(String variable) {
        super(ReservationDetails.class, forVariable(variable));
    }

    public QReservationDetails(Path<? extends ReservationDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservationDetails(PathMetadata metadata) {
        super(ReservationDetails.class, metadata);
    }

}

