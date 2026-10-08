# 🚗 CP5 (Continuação)| API REST de Agendamento de Instruções (AutoEscola3ESPH)
> *Uma API REST robusta desenvolvida com Spring Boot para gerenciar o agendamento de aulas práticas e teóricas, controle de usuários (alunos, instrutores e administradores), autenticação segura e persistência em banco de dados em memória em uma autoescola.*

## 👥 Desenvolvedores
| Turma | Nome | RM |
| :--- | :--- | :--- |
| 3ESPH | Gustavo Viega Martins | RM555885 |
| 3ESPH | Gustavo Yuji Osugi | RM555034 |
| 3ESPH | Kaio Drago Lima Souza | RM556095 |
| 3ESPH | Vitor Rivas Cardoso | RM556404 |

## 📖 1. Contexto
O **AutoEscola3ESPH** é um sistema de backend projetado para otimizar a gestão operacional de uma autoescola. O sistema digitaliza o fluxo de cadastro de usuários, controle de permissões por perfil (Aluno, Instrutor, Admin) e o agendamento de instruções veiculares/teóricas, utilizando um **banco de dados em memória (H2)** para agilidade e testes, além de garantir integridade de dados e segurança nas requisições através de tokens JWT.

## ✨ 2. Funcionalidades (Features)
> *Módulos essenciais, regras de negócio e requisitos implementados na API.*

- **Autenticação e Autorização:** Login seguro baseado em tokens JWT (JSON Web Token) com filtros de segurança customizados (Spring Security).
- **Gestão de Usuários:** Cadastro, listagem, atualização e remoção de perfis (Alunos, Instrutores e Administradores).
- **Agendamento de Instruções:** Marcação de horários de aulas práticas e teóricas vinculadas a instrutores e alunos disponíveis.
- **Controle de Conflitos:** Validação de horários e disponibilidade para evitar sobreposição de agendas.
- **Persistência em Memória:** Banco de dados H2 configurado para execução rápida e isolada.
- **Migrações de Banco de Dados:** Versionamento estruturado utilizando Flyway.
- **Integração Externa:** Implementação e configuração para consumo de API ou WebService externo.
- **Documentação Automática:** Configuração completa de documentação interativa com Swagger.
- **Segurança de Origem:** Configuração de CORS habilitada.
- **Testes Automatizados:** Suíte de exemplos de testes automatizados cobrindo cada uma das entidades do sistema.

## 🛠 3. Tecnologias Utilizadas
> *Tecnologias e bibliotecas que dão suporte à arquitetura e segurança do sistema.*

As seguintes tecnologias e ferramentas foram empregadas no desenvolvimento da API:

- **Linguagem:** Java 17+
- **Framework Principal:** Spring Boot 4.x
- **Persistência:** Spring Data JPA / Hibernate (ORM)
- **Banco de Dados:** H2 Database (in-memory)
- **Migrações e Versionamento de Banco:** Flyway Core
- **Segurança:** Spring Security com autenticação Stateless baseada em JWT (JSON Web Token) através da biblioteca jjwt
- **Documentação:** Springdoc OpenAPI / Swagger
- **Ferramentas de Apoio:** Spring Boot DevTools (para hot-reload em ambiente de desenvolvimento)

## ⚙️ 4. Configuração e Instalação
> *Pré-requisitos e instruções para colocar a aplicação em funcionamento.*

Certifique-se de ter instalado em sua máquina:

- **Java Development Kit** (JDK 17 ou superior)
- Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)
*(Nota: O projeto utiliza banco de dados em memória H2, rodando na porta `8086`, portanto não é necessária a instalação de um SGBD externo).*

### 4.1 Passo a Passo
**1.** Clone o repositório ou abra o projeto na sua IDE.
```bash
git clone [https://github.com/seu-usuario/AutoEscola3ESPH.git](https://github.com/seu-usuario/AutoEscola3ESPH.git)
```
**2.** Abra o projeto na sua IDE como um projeto Maven.

**3.** Execute a classe `AutoEscola3EsphApplication.java` pela IDE ou utilize o terminal executando:
```bash
mvn spring-boot:run
```

## 🗄️ 5. Acesso ao Banco de Dados (H2 Console)
O sistema conta com o console web do H2 habilitado para inspeção das tabelas e dados em tempo de execução. Com a aplicação rodando, acesse no navegador:

👉 URL do Console: http://localhost:8086/h2-console

Parâmetros de Conexão:

- JDBC URL: jdbc: `h2:mem:autoescoladb`
- Username: `sa`
- Password: (deixe em branco)


## 📚 6. Documentação da API (Swagger)
A documentação interativa da API foi gerada para facilitar os testes dos endpoints de autenticação, usuários e agendamentos. Com a aplicação em execução, acesse:

👉 Swagger UI: http://localhost:8086/swagger-ui/index.html (ou conforme o caminho padrão configurado no Springdoc)


## 🔒 7. Segurança e Autenticação (JWT)
> *Arquitetura de controle de acesso Stateless baseada em tokens.*

A API adota um modelo de segurança totalmente Stateless (sem estado na sessão do servidor), ideal para aplicações modernas que atendem clientes web e mobile.

- **Credenciais:** O usuário envia seu login e senha para o endpoint de autenticação.
- **Validação:** O *Spring Security* valida as credenciais contra a base de dados.
- **Emissão de Token:** Sendo válidas, o sistema gera um Token JWT assinado criptograficamente utilizando a chave secreta definida nas propriedades `(api.security.token.secret).`
- **Requisições Protegidas:** O cliente deve anexar o token no cabeçalho (Header) de todas as requisições subsequentes:

```bash
Authorization: Bearer <seu_token_jwt>
```
