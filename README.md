# Getting Started

## Prerequisites
As a prerequisite you need to have installed Java 11 and Apache Maven 3+.
After the installation you can check in the terminal with command `java -version` and `mvn -v`.

### Docker
To run the application, Docker (or Podman) must be installed.
You can verify that docker is installed correctly with command `docker -v`.

## Running the application
1. You need to open the terminal pointing to the project directory.
2. Run the command `mvn clean install`
3. Run the command: `docker-compose up --build`.
The application will start on port **8080**. Server is successfully started when following line appears:
`Server startup in [  ] milliseconds.`

## Endpoint
You can use any tool to call the 'routing' endpoint of the 'countries' application (e.g. Postman, Insomnia, any browser).
The endpoint can be called on localhost URL: `http://localhost:8080/countries/routing/{origin}/{destionation}`, 
where source and destination are country codes.

### Example
`http://localhost:8080/countries/routing/CZE/ITA`

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

