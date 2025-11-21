FROM tomcat:9.0-jdk21-temurin
COPY dist/Pj-SistemasDistribuidos.war /usr/local/tomcat/webapps/Pj-SistemasDistribuidos.war
EXPOSE 8080
