chmod +x mvn*
mvn -N io.takari:maven:wrapper
./mvnw package -Dquarkus.package.type=fast-jar
docker rmi quay.io/qiotcovid19/qiot-datahub-importer:2.0.0-beta
docker build -f src/main/docker/Dockerfile.fast-jar -t quay.io/qiotcovid19/qiot-datahub-importer:2.0.0-beta .
docker push quay.io/qiotcovid19/qiot-datahub-importer:2.0.0-beta