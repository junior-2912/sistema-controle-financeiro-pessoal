# Controle Financeiro Pessoal

Projeto desenvolvido para consolidar os conhecimentos de Java Core.

A aplicação permite registrar receitas e despesas, e gerar relatórios financeiros e exportar transações para um arquivo csv.

Durante o desenvolvimento foram praticados conceitos como POO, Collections, Streams, Exceptions, Manipulação de arquivos e API de datas.

## Funcionalidades

- Cadastro de receitas
- Cadastro de despesas
- Consultar valor total de transações
- Validação de transações
- Consultar despesas por categoria
- Soma de categorias escolhidas pelo usuario
- Ranking das 3 categorias com mais gastos
- Registro das transações exportado para um arquivo CSV

## Conceitos praticados

- Programação Orientada a Objetos
- Collections
- Herança
- Encapsulamento
- Polimorfismo
- Stream API
- Exceptions personalizadas
- API de Datas
- Lambda Expressions
- Enum
- Comparator
- Manipulação de arquivos
- Try-with-resources

## Tecnologias

- Java 21
- Git
- GitHub

## Estrutura do Projeto
```
src
├── application
│   └── Main.java
├── entities
│   ├── GerenciadorFinanceiro.java
│   ├── Transacao.java
│   ├── Receita.java
│   └── Despesa.java
├── enums
│   └── Categoria.java
├── exceptions
│   ├── OperacaoArquivoException.java
│   └── TransacaoInvalidaException.java
└── services
    └── Relatorio.java
```

## Melhorias futuras
- Persistência em banco de dados
- Melhora da interface visual
- Interface gráfica
- Geração de relatórios em PDF
- Filtros por periodo
- Documentação do código

## Objetivo do Projeto
Projeto desenvolvido como parte da minha jornada dos estudos em Java core.
Não foi criado para produção, e sim para consolidar conceitos fundamentais da linguagem através da implementação de uma aplicação que reúne diversos tópicos.  