CREATE
    DATABASE assets;
\c assets;
CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists buildings
(
    id         uuid default uuid_generate_v4() not null
        primary key,
    "name"     text                            not null,
    address    text,
    deleted_at timestamp
);

alter table buildings
    owner to postgres;

create table if not exists storeys
(
    id          uuid default uuid_generate_v4() not null
        primary key,
    "name"      text                            not null,
    building_id uuid                            not null
        references buildings,
    deleted_at  timestamp
);

alter table storeys
    owner to postgres;

create table if not exists rooms
(
    id         uuid default uuid_generate_v4() not null
        primary key,
    "name"     text                            not null,
    storey_id  uuid                            not null
        references storeys,
    deleted_at timestamp
);

alter table rooms
    owner to postgres;
