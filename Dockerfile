FROM tomcat:9.0-jdk21-temurin
COPY dist/Escanear.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080







