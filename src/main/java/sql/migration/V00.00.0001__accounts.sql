CREATE TABLE ACCOUNT1
(
    id              BIGSERIAL NOT NULL,
    name            VARCHAR(255) NOT NULL,
    amount          BIGSERIAL NOT NULL,
    date_open       timestamp,
    date_closed     timestamp,
    primary key (id)
);

 create table operations (
    id int8 not null,
    account_id int8,
    summ_credit int8,
    summ_debit int8,
    primary key (id)
);