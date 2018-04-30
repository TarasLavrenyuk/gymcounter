create table users
(
    user_id serial not null
        constraint users_pkey
        primary key,
    username varchar not null,
    password varchar not null,
    first_name varchar,
    last_name varchar,
    user_role varchar not null,
    sex varchar,
    email varchar not null,
    weight real,
    height real
)
;

create table exercises
(
    exercise_id serial not null
        constraint exercises_pkey
        primary key,
    name varchar not null,
    description varchar,
    creator integer
)
;

create table sets
(
    set_id serial not null
        constraint sets_pkey
        primary key,
    user_id integer not null,
    exercise_id varchar not null,
    set_time timestamp not null,
    reps integer not null,
    training_id varchar not null
)
;

create table trainings
(
    training_id serial not null
        constraint trainings_pkey
        primary key,
    description varchar,
    date date not null
)
;

create table user_params
(
    user_id integer not null,
    height real,
    weight integer,
    date date not null
)
;

