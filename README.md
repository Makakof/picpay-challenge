# picpay-challenge
Desafio da picpay com o intuito de expandir meu conhecimento e desenvolver um portfólio

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=%20CONCLUIDO&color=GREEN&style=for-the-badge)

Esse projeot foi desenvolvido usando as seguintes tecnologias e suas versões respectivamente

- [Java](https://www.java.com/pt-BR/) versão 17 
- [Spring](https://spring.io/) versão 3.3.4
- [Postgress](https://www.postgresql.org/) versão 17
- [Docker](https://www.docker.com/) (Opcional)

## Regras de negócio
O PicPay Simplificado é uma plataforma de pagamento simplificada. Nela é possivel depositar e realizar transférencia de dinheiro entre usuários. Temos 2 tipos de usuários: os comuns e os lojista, ambos tem carteira com dinheiro mas o lojista somente pode receber transferência.

Para ver o desafio por completo [clique aqui](https://github.com/PicPay/picpay-desafio-backend)

## Instalação

### Clonando este repositório

Abra o terminal de seu computador no diretório que desejar e execute o seguinte comando

```
git clone your-project-url-in-github
```

Após essa etapa abra a aplicação usando uma IDe de seu gosto capaz de executar a linguagem Java, eu recomendo [InteliJ](https://www.jetbrains.com/pt-br/idea/)

### Gerando executável

Abra a aplicação na IDe e siga os seguintes passos na interface, aqui ensinarei no Intelij, caso use outra, procure "Como gerar arquivo Jar na "nome da sua IDE"

1. File > Project Structure > Project Settings > Artifacts > Clique no "+" > Jar > From modules with dependencies... > selecione qual a classe Main > OK

### Instalando o banco

A aplicação esta preparada para usar tanto o postgress diretamente quando docker.

#### Usando Postgress

Instala o postgress e configure o banco usando o name e password que estão no arquivo "properties" do projeto

#### Usando Docker

Após baixar e instalar o docker siga os passos do video a seguir [clique aqui](https://www.youtube.com/watch?v=KlbL-8CEjN0)

### Rodando a aplicação

Com o banco aberto, abra o terminal no diretório que se encontra o arquivo.jar que foi gerado no passo 1 e execute o seguinte comando

```
java -jar NomeDoArquivo.jar
```

### Swagger

Para acessar os end points basta ir para a url: [http:localhost/3000/api](http:localhost/3000/api)