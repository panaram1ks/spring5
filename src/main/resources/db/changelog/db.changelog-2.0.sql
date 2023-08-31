--liquibase formatted sql

--changeset dmatveyenka:1
ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE users
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE users
    ADD COLUMN created_by varchar(32);

ALTER TABLE users
    ADD COLUMN modified_by varchar(32);