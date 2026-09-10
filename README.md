# 🚗API REST de Agendamento de Instruções (AutoEscola3ESPH)
> *Uma API REST robusta desenvolvida com Spring Boot para gerenciar o agendamento de aulas práticas e teóricas, controle de usuários (alunos, instrutores e administradores) e autenticação segura em uma autoescola.*

## 👥Desenvolvedores
| Turma | Nome | RM |
| :--- | :--- | :--- |
| 3ESPH | Gustavo Viega Martins | RM555885 |
| 3ESPH | Gustavo Yuji Osugi | RM555034 |
| 3ESPH | Kaio Drago Lima Souza | RM556095 |
| 3ESPH | Vitor Rivas Cardoso | RM556404 |

## 📖1. Contexto
O **AutoEscola3ESPH** é um sistema de backend projetado para otimizar a gestão operacional de uma autoescola. O sistema digitaliza o fluxo de cadastro de usuários, controle de permissões por perfil (Aluno, Instrutor, Admin) e o agendamento de instruções veiculares/teóricas, garantindo integridade de dados e segurança nas requisições através de tokens JWT.

## ✨2. Funcionalidades (Features)
> *Módulos essenciais e regras de negócio implementadas na API.*

- **Autenticação e Autorização:** Login seguro baseado em tokens JWT (JSON Web Token) com filtros de segurança customizados (Spring Security).
- **Gestão de Usuários:** Cadastro, listagem, atualização e remoção de perfis (Alunos, Instrutores e Administradores).
- **Agendamento de Instruções:** Marcação de horários de aulas práticas e teóricas vinculadas a instrutores e alunos disponíveis.
- **Controle de Conflitos:** Validação de horários e disponibilidade para evitar sobreposição de agendas.
- **Migrações de Banco de Dados:** Versionamento estruturado utilizando Flyway.

## 🛠3. Tecnologias Utilizadas
> *Tecnologias e bibliotecas que dão suporte à arquitetura e segurança do sistema.*

As seguintes tecnologias e ferramentas foram empregadas no desenvolvimento da API:

- **Linguagem:** Java 17+
- **Framework Principal:** Spring Boot 4.x
- **Persistência:** Spring Data JPA / Hibernate (ORM)
- **Banco de Dados:** MySQL 8+
- **Migrações e Versionamento de Banco:** Flyway Core
- **Segurança:** Spring Security com autenticação Stateless baseada em JWT (JSON Web Token) através da biblioteca jjwt
- **Ferramentas de Apoio:** Spring Boot DevTools (para hot-reload em ambiente de desenvolvimento)


## ⚙️ 4. Configuração e Instalação
> *Pré-requisitos e instruções para colocar a aplicação em funcionamento.*

Certifique-se de ter instalado em sua máquina:

- **Java Development Kit** (JDK 17 ou superior)
- **MySQL Server** (Rodando localmente ou via Docker na porta `3306`)
- Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

### 4.1 Passo a Passo
**1.** Clone o repositório ou abra o projeto na sua IDE (IntelliJ IDEA, Eclipse, VS Code).
```bash
git clone [https://github.com/seu-usuario/AutoEscola3ESPH.git](https://github.com/seu-usuario/AutoEscola3ESPH.git)
```

**2.**  Abra o projeto na sua IDE como um projeto **Maven**.

**3.** Crie o banco de dados no **MySQL Workbench** ou via terminal:
```bash
CREATE DATABASE autoescola3esph;
```

**4.** Execute a classe `AutoEscola3EsphApplication.java` pela **IDE** ou utilize o terminal executando:
```bash
mvn spring-boot:run
```

## 🔒 5. Segurança e Autenticação (JWT)
> *Arquitetura de controle de acesso Stateless baseada em tokens.*

A API adota um modelo de segurança totalmente *Stateless* (sem estado na sessão do servidor), ideal para aplicações modernas que atendem clientes web e mobile.

- **Credenciais:** O usuário envia seu login e senha para o endpoint de autenticação.
- **Validação:** O Spring Security valida as credenciais contra a base de dados.
- **Emissão de Token:** Sendo válidas, o sistema gera um Token JWT assinado criptograficamente utilizando a chave secreta definida nas propriedades `(api.security.token.secret)`.
- **Requisições Protegidas:** O cliente deve anexar o token no cabeçalho (Header) de todas as requisições subsequentes:
