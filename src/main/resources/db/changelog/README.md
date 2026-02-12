# Liquibase Changelog Structure

Este diretório contém os changelogs do Liquibase para controle de versão do banco de dados.

## Estrutura

```
db/changelog/
├── db.changelog-master.yaml (arquivo principal)
└── changes/
    └── v1.0/
        └── *.sql (seus arquivos SQL aqui)
```

## Como adicionar mudanças

1. Crie um novo arquivo SQL em `changes/v1.0/`
2. Inclua-o no `db.changelog-master.yaml`
3. Execute a aplicação para aplicar as mudanças

## Exemplo de arquivo SQL

**002-create-users-table.sql:**
```sql
-- changeset jean:002-create-users-table
-- comment: Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_email ON users(email);
-- rollback DROP TABLE users;
```

**Para incluir no master:**
```yaml
databaseChangeLog:
  - include:
      file: db/changelog/changes/v1.0/001-initial-setup.sql
  - include:
      file: db/changelog/changes/v1.0/002-create-users-table.sql
```

## Formato dos comentários

- `-- changeset autor:id` - Define o changeset (obrigatório)
- `-- comment: descrição` - Adiciona comentário
- `-- rollback <comando>` - Define o rollback
- `-- preconditions onFail:HALT` - Adiciona pré-condições

## Múltiplos changesets em um arquivo

```sql
-- changeset jean:003-create-wallets
CREATE TABLE wallets (...);
-- rollback DROP TABLE wallets;

-- changeset jean:004-create-transactions
CREATE TABLE transactions (...);
-- rollback DROP TABLE transactions;
```
