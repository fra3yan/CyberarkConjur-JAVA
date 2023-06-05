Build & Run docker 
docker build -f Dockerfile -t docker-demopsdk .
docker run -p 8081:8080 docker-demopsdk  