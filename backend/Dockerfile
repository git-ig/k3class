FROM tomcat:9.0-jre11-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY build/libs/ROOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

# CMD ["catalina.sh", "run"]
