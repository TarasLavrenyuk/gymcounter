CREATE TABLE exercises
(
    exercise_id INTEGER DEFAULT nextval('exercises_exercise_id_seq'::regclass) PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    description VARCHAR
);
CREATE TABLE sets
(
    set_id INTEGER DEFAULT nextval('sets_set_id_seq'::regclass) PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    exercise_id INTEGER NOT NULL,
    set_time TIMESTAMP NOT NULL,
    reps INTEGER NOT NULL,
    training_id INTEGER NOT NULL
);
CREATE TABLE trainings
(
    training_id INTEGER DEFAULT nextval('trainings_training_id_seq'::regclass) PRIMARY KEY NOT NULL,
    description VARCHAR,
    date DATE NOT NULL
);
CREATE TABLE user_params
(
    user_id INTEGER NOT NULL,
    height REAL,
    weight INTEGER,
    date DATE NOT NULL
);
CREATE TABLE users
(
    user_id INTEGER DEFAULT nextval('users_user_id_seq'::regclass) PRIMARY KEY NOT NULL,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    first_name VARCHAR,
    last_name VARCHAR,
    user_role VARCHAR NOT NULL,
    sex VARCHAR,
    email VARCHAR NOT NULL,
    weight REAL,
    height REAL
);
