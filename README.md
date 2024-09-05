# Controle de disponibilidade de laboratórios

API para cadastrar laboratórios de universidade e seus bolsistas, e através de sessões informar quais ambientes estão disponiveis para uso.

## Diagrama de Classes (Domínio da API)
```mermaid
classDiagram
    User --|> Supervisor
    Supervisor --> Session
    Laboratory --> Session

    class User{
        -int id
        -String username
        -String password
        -Role role
        -StatusUser statusUser
    }

    class Supervisor{
        -int id
        -String registration
        -String name
        -String course
        -String entryperiod
    }
    
    class Laboratory{
        -int id
        -String name
        -String description
    }

    class Session{
        -boolean status
        -ShiftSession shift
        -Laboratory laboratory
        -Supervisor supervisor
    }
´´´

## Execução local com Postgresql

Criar banco de dados e disponibilizar informações em `application.properties`. Através do flyway as tabelas serão criadas na primeira execução, assim como um user admin(username=admin, password=admin) para testes.

Visualize os endpoints em `http://localhost:8080/swagger-ui/index.html#/`

Para requisições utilize o token gerado com o login de admin. No insomnia, na aba Auth da requesição, adicione um Bearer Token e preencha o com o token e "Bearer" em prefix.
