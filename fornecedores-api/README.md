
## Resumo da Aplicação
- Esse projeto tem como objetivo realizar um cadastro básico de fornecedores para produtos
- O intuito desse projeto de fornecedores é servir como API externa para uma outra API de Pedidos.
- O objetivo da API de pedidos é criar pedidos para um determinado produto retornando o melhor fornecedor para cada produto

## Executar aplicação
- Abrir um terminal na pasta raiz do projeto
- Entrar na raiz do projeto e executar "mvn clean install" para gerar o target do projeto
- Ainda com o terminal na raiz do projeto, executar o comando "docker compose -f docker-compose-dev.yml up -d" (Criar as imagens e subir todos os containers)

## Swagger
- http://localhost:81/fornecedores-api/swagger-ui/index.html
