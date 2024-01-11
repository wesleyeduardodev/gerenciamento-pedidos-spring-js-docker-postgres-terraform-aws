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
echo "Subindo containers..."
docker compose -f docker-compose-dev.yml up -d
echo "Containers OK!"

echo ""
read -p "Pressione enter para encerrar..."