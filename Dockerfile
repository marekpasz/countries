FROM tomcat:9.0.48-jdk11-openjdk-slim
COPY target/countries.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]