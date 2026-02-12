-- liquibase formatted sql

-- changeset jean:001-create-appuser-table
-- comment: Create appuser table with audit fields
CREATE TABLE appuser (
    id BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(255) NOT NULL,
    inclusao_data TIMESTAMP NOT NULL,
    inclusao_usuario_id BIGINT,
    alteracao_data TIMESTAMP,
    alteracao_usuario_id BIGINT
);

CREATE INDEX idx_appuser_username ON appuser(username);
CREATE INDEX idx_appuser_email ON appuser(email);
-- rollback DROP TABLE appuser;
