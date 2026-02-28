-- liquibase formatted sql

-- changeset jean:001-create-appuser-table
-- comment: Create appuser table with audit fields
CREATE TABLE appuser (
    id BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT
);

CREATE INDEX idx_appuser_username ON appuser(username);
CREATE INDEX idx_appuser_email ON appuser(email);
-- rollback DROP TABLE appuser;
