#!bin/bash

cd ..
echo ""
echo "Abrindo diretório terraform..."
cd deploy/terraform
echo "Diretório encontrado com sucesso!"

echo ""
echo "Executando terraform destroy --auto-approve..."
terraform destroy --auto-approve
echo "terraform destroy --auto-approve executado com sucesso!"

echo ""
read -p "Pressione enter para encerrar..."