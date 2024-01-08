
## Requisitos Gerais
- Docker/WSL2 instalado e configurado no ambiente Windows. Sugestão(https://github.com/codeedu/wsl2-docker-quickstart)
- Java 17 configurado nas variáveis de ambiente do Windows. Sugestão(https://corretto.aws/downloads/latest/amazon-corretto-17-x64-windows-jdk.zip)
- Maven 3+ configurado nas variáveis de ambiente do Windows. Sugestão(https://dlcdn.apache.org/maven/maven-3/3.9.6/source/apache-maven-3.9.6-src.zip)

## Resumo da Aplicação
- Esse projeto tem como objetivo realizar um cadastro básico de fornecedores para produtos
- O intuito desse projeto de fornecedores é servir como API externa para uma outra API de Pedidos.
- O objetivo da API de pedidos é criar pedidos para um determinado produto retornando o melhor fornecedor para cada produto
- Demais detalhes sobre a API de pedidos: https://github.com/wesleyeduardodev/api-desafio-pedidos

## Executar aplicação
- Abrir um terminal na pasta raiz do projeto
- Entrar na raiz do projeto e executar "mvn clean install" para gerar o target do projeto
- Ainda com o terminal na raiz do projeto, executar o comando "docker compose -f docker-compose-dev.yml up -d" (Criar as imagens e subir todos os containers)

## Swagger
- http://localhost:81/fornecedores-api/swagger-ui/index.html

## Comandos Docker que podem ser utéis
- Gerar Imagem: docker build -t wesleyeduardodev/fornecedores-api:latest .
- Remover todos os containers: docker rm $(docker ps -a -q) -f
- Remover todos as imagens: docker rmi $(docker images -q) -f
- Remover todos os volumes: docker volume rm $(docker volume ls -qf dangling=true)
- docker logs --follow fornecedores-api