#!bin/bash

echo ""
echo "Subindo containers..."
docker compose -f docker-compose.yml up -d
echo "Containers gerado!"

echo ""
read -p "Pressione enter para encerrar e aguarde um tempinho para que os containers iniciem completamente...Visite o arquivo README.md com instruções de uso do projeto."