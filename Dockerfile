FROM jboss/keycloak:latest

# Copie os arquivos de configuração personalizados para o container
COPY realm.json /opt/jboss/keycloak/realm.json

# Defina as variáveis de ambiente do Keycloak
ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin

# Importe o arquivo de configuração personalizado no Keycloak
RUN /opt/jboss/keycloak/bin/standalone.sh -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=/opt/jboss/keycloak/realm.json -Dkeycloak.migration.strategy=OVERWRITE_EXISTING

# Exponha a porta do Keycloak
EXPOSE 8080

# Inicie o Keycloak
CMD ["-b", "0.0.0.0"]
ENTRYPOINT ["/opt/jboss/keycloak/bin/standalone.sh"]