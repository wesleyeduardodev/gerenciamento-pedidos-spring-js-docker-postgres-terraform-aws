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
echo "Abrindo diretório terraform..."
cd deploy/terraform
echo "Diretório encontrado com sucesso!"

echo ""
echo "Executando terraform init..."
terraform init
echo "terraform init executado com sucesso!"

echo ""
echo "Executando terraform plan..."
terraform plan
echo "terraform plan executado com sucesso!"

echo ""
echo "Executando terraform apply --auto-approve..."
terraform apply --auto-approve
echo "terraform apply --auto-approve executado com sucesso!"

echo ""
read -p "Pressione enter para encerrar..."