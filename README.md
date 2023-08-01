# Back-test API

**Description:** This API was created for testing purposes.

## Technologies Involved

This API was developed using the following technologies:

* Java 17;
* Maven
* Spring Boot 3.1.2;
* MySQL;

## Execution

To run the application, we have provided a Docker image. To execute it, follow these steps:

* mvn clean package
* docker build -t back-test-app .

Next, we execute the "docker-compose.yml" file located at:

````
../src/main/resources/docker-compose.yml
````

From this directory, simply run the command:

````
docker-compose up -d
````

By doing this, we will have MySQL and the application running. For more details on the available resources, consult the
documentation:

````

http://localhost:8080/swagger-ui/index.html#

````
