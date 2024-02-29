docker network create agaat-services-network

docker compose -f app/docker-compose.yml up -d --build