[![Coverage Status](https://coveralls.io/repos/github/danielsire/url-shortener-api/badge.svg?branch=master)](https://coveralls.io/github/danielsire/url-shortener-api?branch=master)

# URL-Shortener challenge

Run `docker-compose up` to create PostgreSQL databases

### Running App

Run `mvn clean install`

Run `java -jar target/urlShortener-0.0.1-SNAPSHOT.jar`

Tomcat will start on port *8081*

### Running App Docker

Run `mvn clean install`

Run `docker build -t url-shortener-chalenge .`

Run `docker run -p 8081:8081 url-shortener-chalenge`

Tomcat will start on port *8081*