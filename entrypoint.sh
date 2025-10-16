#!/bin/sh
# entrypoint.sh

# Espera o host 'oracle-db' estar disponível na porta 1521
echo "Aguardando o banco de dados Oracle iniciar..."
while ! nc -z oracle-db 1521; do
  sleep 1
done
echo "Banco de dados Oracle iniciado!"

sleep 30

# Executa o comando principal da aplicação (o .jar)
exec "$@"