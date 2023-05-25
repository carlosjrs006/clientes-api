FROM jboss/keycloak:15.0.2

USER root

# Instalar o curl
RUN apt-get update && apt-get install -y curl

USER jboss

# Restante das instruções do Dockerfile
ENV KC_DB=mysql

# ...
RUN curl -sL https://github.com/aerogear/keycloak-metrics-spi/releases/download/2.5.3/keycloak-metrics-spi-2.5.3.jar -o /opt/keycloak/providers/keycloak-metrics-spi-2.5.3.jar

# ...

RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:latest
COPY --from=builder /opt/keycloak/ /opt/keycloak/
WORKDIR /opt/keycloak
# for demonstration purposes only, please make sure to use proper certificates in production instead
RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore
# change these values to point to a running postgres instance
ENV KC_DB_URL=jdbc:mysql://root:fHv5SPY3fWPJe2sL8eVw@containers-us-west-7.railway.app:7328/keycloak
ENV KC_DB_USERNAME=root
ENV KC_DB_PASSWORD=fHv5SPY3fWPJe2sL8eVw
ENV KC_HOSTNAME=localhost
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start"]