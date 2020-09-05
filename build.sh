./mvnw clean package -Pnative  -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=docker
docker rmi quay.io/qiot/qiot-datahub-importer
docker build -f src/main/docker/Dockerfile.native -t quay.io/qiot/qiot-datahub-importer:1.0.1 .
docker push quay.io/qiot/qiot-datahub-importer:1.0.1
#docker run -it --rm -p 5000:5000 --net host quay.io/qiot/qiot-datahub-importer
