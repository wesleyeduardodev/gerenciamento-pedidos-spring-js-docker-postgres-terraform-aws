
## Requisitos Gerais
- Docker/WSL2 instalado e configurado no ambiente Windows. Sugestão(https://github.com/codeedu/wsl2-docker-quickstart)
- Java 17 configurado nas variáveis de ambiente do Windows. Sugestão(https://corretto.aws/downloads/latest/amazon-corretto-17-x64-windows-jdk.zip)
- Maven 3+ configurado nas variáveis de ambiente do Windows. Sugestão(https://dlcdn.apache.org/maven/maven-3/3.9.6/source/apache-maven-3.9.6-src.zip)

## Configuração da API Externa de Fornecedores de um Produto
- Realizar o clone do projeto: https://github.com/wesleyeduardodev/fornecedores-api
- Abrir um terminal na pasta raiz do projeto
- Entrar na raiz do projeto e executar "mvn clean install" para gerar o target do projeto
- Executar o comando "docker build -t wesleyeduardodev/fornecedores-api:latest ." para gerar imagem da aplicação

## Executar o Projeto API de Pedidos
- Abrir um terminal na pasta raiz do projeto
- Executar comando "mvn clean install" para gerar o target do projeto.
- Ainda com o terminal na raiz do projeto, executar o comando "docker compose -f docker-compose-dev.yml up -d" (Criar as imagens e subir todos os containers)
- Obs: Caso ocorra problemas ao subir todos os container de forma simultanea basta subir um container de cada vez. (As vezes pode ocorrer problemas onde um container precisa de outro que ainda não subiu totalmente - Isso será resolvido posteriormente usando o recurso "wait-for-it")

## Executar o Projeto ambiente Prod
- docker compose -f docker-compose-prod-com-docker-hub.yml up -d

## Links para uso da aplicação
- Dados de login para autenticação básica: user: admin e senha: admin
- Criar novos pedidos: http://localhost:8080/#!/pedidos/novo
- Listar Pedidos: http://localhost:8080/#!/pedidos
- Swagger: http://localhost:8080/pedidos-api/swagger-ui.html

## Comandos Docker que podem ser utéis
- Remover todos os containers: docker rm $(docker ps -a -q) -f
- Remover todos as imagens: docker rmi $(docker images -q) -f
- Remover todos os volumes: docker volume rm $(docker volume ls -qf dangling=true)