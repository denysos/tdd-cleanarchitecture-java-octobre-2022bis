create table if not exists condominiums
(
    id            uuid    not null
        constraint condominiums_pk
            primary key,
    yearly_budget numeric not null
);


create table if not exists calls_for_funds
(
    id             uuid    not null
        constraint calls_for_funds_pk
            primary key,
    condominium_id uuid    not null,
    amount         numeric not null,
    quarter        integer not null,
    occurred_on    timestamp not null
);

