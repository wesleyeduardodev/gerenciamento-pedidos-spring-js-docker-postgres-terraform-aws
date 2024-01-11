#!bin/bash

cd ..
cd fornecedores-api
echo ""
echo "Gerando JAR da aplicação API Fornecedores..."
mvn clean install
echo ""
echo "JAR da aplicação API Fornecedores gerado com sucesso!"

echo ""
echo "Gerando Imagem da aplicação API Fornecedores..."
docker build -t wesleyeduardodev/fornecedores-api:latest .
echo ""
echo "Imagem da aplicação API Fornecedores gerada com sucesso!"

cd ..
cd pedidos-api
echo ""
echo "Gerando JAR da aplicação API Pedidos..."
mvn clean install
echo ""
echo "JAR da aplicação API Pedidos gerado com sucesso!"

echo ""
echo "Gerando Imagem da aplicação API Pedidos..."
docker build -t wesleyeduardodev/pedidos-api:latest .
echo ""
echo "Imagem da aplicação API Pedidos gerada com sucesso!"

echo ""
echo "Realizando login no docker..."
docker login
echo ""
echo "Login realizado com sucesso!"

echo ""
echo "Atualizando imagem da API de Fornecedores no Docker Hub..."
docker push wesleyeduardodev/fornecedores-api:latest
echo ""
echo "Imagem da API de Fornecedores atualizada com sucesso!"

echo ""
echo "Atualizando imagem da API de Pedidos no Docker Hub..."
docker push wesleyeduardodev/pedidos-api:latest
echo ""
echo "Imagem da API de Pedidos atualizada com sucesso!"

cd ..
echo ""
echo "Subindo containers..."
docker compose -f docker-compose-dev.yml up -d
echo "Containers OK!"

echo ""
read -p "Pressione enter para encerrar..."