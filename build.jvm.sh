chmod +x mvn*
mvn -N io.takari:maven:wrapper
./mvnw clean package
docker rmi quay.io/qiotcovid19/qiot-datahub-importer:2.0.0-alpha
cd qiot-datahub-importer
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/qiotcovid19/qiot-datahub-importer:2.0.0-alpha .
cd ..
docker push quay.io/qiotcovid19/qiot-datahub-importer:2.0.0-alpha
