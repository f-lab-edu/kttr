create table member
(
    id               bigint auto_increment,
    sign_in_id       varchar(30),
    sign_in_password varchar(150),
    membership_code  varchar(8),
    email            varchar(30),
    phone_number     varchar(15),
    member_status    varchar(10),
    primary key (id)
);

create table train_ticket
(
    id             bigint auto_increment,
    name           varchar(10),
    max_quantity   int,
    issue_quantity int,
    primary key (id)
);

create table reservation_details
(
    id               bigint auto_increment,
    reservation_code varchar(50),
    member_id        bigint,
    ticket_id        bigint,
    primary key (id)
);

