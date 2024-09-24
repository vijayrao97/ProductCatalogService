CREATE TABLE category
(
    id              BIGINT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    state           INT NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jc_instructor
(
    userid  BIGINT NOT NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_jc_instructor PRIMARY KEY (userid)
);

CREATE TABLE jc_mentor
(
    userid BIGINT NOT NULL,
    hours  BIGINT NULL,
    CONSTRAINT pk_jc_mentor PRIMARY KEY (userid)
);

CREATE TABLE jc_ta
(
    userid BIGINT NOT NULL,
    hours DOUBLE NOT NULL,
    CONSTRAINT pk_jc_ta PRIMARY KEY (userid)
);

CREATE TABLE jc_user
(
    id    BIGINT NOT NULL,
    dtype VARCHAR(31) NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_jc_user PRIMARY KEY (id)
);

CREATE TABLE msc_instructor
(
    id      BIGINT NOT NULL,
    email   VARCHAR(255) NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_msc_instructor PRIMARY KEY (id)
);

CREATE TABLE msc_mentor
(
    id    BIGINT NOT NULL,
    email VARCHAR(255) NULL,
    hours BIGINT NULL,
    CONSTRAINT pk_msc_mentor PRIMARY KEY (id)
);

CREATE TABLE msc_ta
(
    id    BIGINT NOT NULL,
    email VARCHAR(255) NULL,
    hours DOUBLE NOT NULL,
    CONSTRAINT pk_msc_ta PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                BIGINT NOT NULL,
    created_at        datetime NULL,
    last_updated_at   datetime NULL,
    state             INT NULL,
    title             VARCHAR(255) NULL,
    `description`     VARCHAR(255) NULL,
    imageurl          VARCHAR(255) NULL,
    amount DOUBLE NOT NULL,
    category_id       BIGINT NULL,
    is_prime_specific BIT(1) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    id        BIGINT NOT NULL,
    user_type INT NULL,
    email     VARCHAR(255) NULL,
    company   VARCHAR(255) NULL,
    hours DOUBLE NOT NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE tpc_instructor
(
    id      BIGINT NOT NULL,
    email   VARCHAR(255) NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id    BIGINT NOT NULL,
    email VARCHAR(255) NULL,
    hours BIGINT NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_ta
(
    id    BIGINT NOT NULL,
    email VARCHAR(255) NULL,
    hours DOUBLE NOT NULL,
    CONSTRAINT pk_tpc_ta PRIMARY KEY (id)
);

CREATE TABLE tpc_user
(
    id    BIGINT NOT NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (id)
);

ALTER TABLE jc_instructor
    ADD CONSTRAINT FK_JC_INSTRUCTOR_ON_USERID FOREIGN KEY (userid) REFERENCES jc_user (id);

ALTER TABLE jc_mentor
    ADD CONSTRAINT FK_JC_MENTOR_ON_USERID FOREIGN KEY (userid) REFERENCES jc_user (id);

ALTER TABLE jc_ta
    ADD CONSTRAINT FK_JC_TA_ON_USERID FOREIGN KEY (userid) REFERENCES jc_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);