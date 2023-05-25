# Use a imagem oficial do Keycloak
FROM jboss/keycloak

# Adicione personalizações, se necessário
# EXEMPLO: Copie arquivos personalizados para o diretório de implantação do Keycloak
COPY ./realm.json /opt/jboss/keycloak/standalone/deployments/

# Defina as variáveis de ambiente, se necessário
# EXEMPLO: Defina as variáveis de ambiente para conexão com banco de dados
ENV DB_VENDOR=mysql
ENV DB_ADDR=jdbc:mysql://root:fHv5SPY3fWPJe2sL8eVw@containers-us-west-7.railway.app:7328/keycloak
ENV DB_DATABASE=keycloak
ENV DB_USER=root
ENV DB_PASSWORD=fHv5SPY3fWPJe2sL8eVw

# Exponha a porta do Keycloak
EXPOSE 8080

# Comando de inicialização do Keycloak
CMD ["-b", "0.0.0.0"]
