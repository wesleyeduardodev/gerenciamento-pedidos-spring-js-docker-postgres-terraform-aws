#!bin/bash


echo ""
echo "Subindo containers..."
docker compose -f docker-compose.yml up -d
echo "Containers OK!"

echo ""
read -p "Pressione enter para encerrar..."