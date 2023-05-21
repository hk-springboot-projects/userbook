create table user_detail
(
    id bigint not null AUTO_INCREMENT,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    gender varchar(10),
    city varchar(255),
    country varchar(255),
    primary key (id)
);