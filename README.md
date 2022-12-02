# Getting Started

## Docker
To run the application, docker must be installed.
You can verify it's installed correctly with command `docker -v`.

## Running the application
You need to open the terminal pointing to the project directory and run the command: `docker-compose up --build`.
The application will start on port **8080**.

## Endpoint
You can use any tool to call the 'routing' endpoint of the 'countries' application (e.g. Postman or Insomnia).
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

