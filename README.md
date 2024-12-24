# Sistema de Helpdesk - README

## Descrição do Projeto

O **Sistema de Helpdesk** é uma aplicação desenvolvida para gerenciar solicitações de suporte técnico de forma organizada e eficiente. Ele funciona como uma "central de atendimento digital", onde clientes podem relatar problemas técnicos e as empresas podem gerenciar essas solicitações através de fluxos estruturados de atendimento.

### Contexto de Uso

Este sistema é ideal para:
- **Empresas de Tecnologia**: Onde clientes podem relatar problemas com softwares, bugs, ou orientações técnicas.
- **Departamentos de TI de Grandes Empresas**: Que precisam gerenciar problemas de infraestrutura, suporte a sistemas internos e solicitações de equipamentos novos.

### Funcionalidades Principais

1. **Registro de Chamados**  
   Cada chamado possui as seguintes informações:
    - **Título descritivo**: Uma breve descrição do problema.
    - **Descrição detalhada**: Explicação mais detalhada do problema enfrentado.
    - **Categoria**: Tipo de problema (Hardware, Software, Rede, etc.).
    - **Prioridade**: Níveis que variam de Baixa, Média, Alta e Crítica.
    - **Status**: Pode ser Aberto, Em Andamento, Resolvido ou Fechado.

2. **Fluxo de Atendimento**
    - **Cliente abre um chamado**: O cliente cria uma solicitação com os detalhes do problema.
    - **Classificação automática**: O sistema classifica o chamado automaticamente com base em categorias e prioridades.
    - **Notificação para técnico**: A equipe de suporte é notificada sobre o chamado.
    - **Técnico analisa e assume o chamado**: Um técnico se encarrega do problema e registra as ações realizadas.
    - **Resolução e fechamento**: Após a resolução, o chamado é fechado e o cliente recebe feedback.

3. **Níveis de Atendimento**
    - **Nível 1**: Suporte básico para problemas simples.
    - **Nível 2**: Problemas mais complexos que requerem conhecimentos técnicos específicos.
    - **Nível 3**: Problemas avançados que podem necessitar desenvolvimento ou engenharia.

## Tecnologias Utilizadas

- **Back-end**: Java com Spring Boot
- **Banco de Dados**: PostgreSQL
- **Autenticação e Autorização**: JWT
- **Ferramentas de Versionamento**: Git

## Como Rodar o Projeto Localmente

1. **Pré-requisitos**
    - JDK 11 ou superior
    - Maven ou Gradle
    - Banco de dados PostgreSQL configurado

2. **Clone o repositório**
   ```bash  
   git clone https://github.com/J0aoPaulo/hermes
   cd sistema-de-helpdesk  
