# WS Price Project

This project is a price service built with **Spring Boot 3.4+** and **Java 21**. It uses a hexagonal architecture organized into three main modules: **Application**, **Domain**, and **Infrastructure**. The service exposes a REST API that allows querying product prices based on parameters such as the application date, product ID, and brand ID.

## Architecture

The hexagonal architecture has been chosen to promote separation of concerns and facilitate code maintainability and scalability. The modules are organized as follows:

1. **Application**: Contains the application logic and use cases. It handles interactions with the domain and serves as the entry point for service operations.

2. **Domain**: Defines domain objects and business rules. It is independent of infrastructure and application, ensuring flexibility and maintainability.

3. **Infrastructure**: Manages interactions with external systems, such as databases, APIs, and network services. It implements the interfaces defined in the application module and provides concrete solutions for persistence and other technical functionalities.

![img_2.png](img/img_2.png)

## Dependencies

The project uses several key dependencies to enhance its functionality and facilitate development:

- **Lombok**: Eases code writing by reducing the amount of boilerplate code needed, such as getters, setters, and constructors.

- **MapStruct**: Provides a simple way to map between objects, which is useful for converting between domain entities and DTOs (Data Transfer Objects).

- **Spring Data JPA**: Facilitates interaction with databases through the implementation of repositories and entity management.

- **Spring Boot Actuator**: Provides monitoring and metrics functionalities, allowing for the supervision of the application's state and the exposure of metrics through endpoints.

- **SonarQube**: Used for code quality analysis and test coverage, helping to maintain quality standards in development.

## API First

This project follows an **API First** approach, meaning that the API definition is created before the implementation of the code. This approach allows development teams and stakeholders to collaborate on the API specification, ensuring that everyone is aligned on the requirements and expected behavior.

The API specification can be found in the file:

- **[`price-api.yaml`](./infrastructure/src/main/resources/open-api/price-api.yaml)**: This file contains the API definition, including endpoints, parameters, and expected responses.


## How to Run the Project

### 1. Clone the Repository

```bash
git clone https://github.com/jhonatanMD/ws-price.git
cd ws-price
```

### 2. Build the Project

Run a clean build of the project:

```bash
mvn clean install
```

### 3. Build Docker Images

Build all necessary images without using cache:

```bash
docker-compose build --no-cache
```

### 4. Start the Environment with Docker

Start the necessary internal services (InfluxDB, Grafana, PostgreSQL, and SonarQube):

```bash
docker-compose up -d influxdb grafana postgres sonarqube
```

**Note**: The price service uses H2 as an in-memory database, while PostgreSQL is used exclusively for SonarQube.

### 5. Run Coverage Analysis with SonarQube

Access SonarQube at: [http://localhost:9000](http://localhost:9000)

User: `admin`

Password: `admin` or `admin1` (depending on the initial configuration)

Request a password change if necessary and then run the analysis:

```bash
mvn verify sonar:sonar \
  -Dsonar.projectKey=ws-price \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=admin \
  -Dsonar.password=admin1
```

![img.png](img/sonar.png)

### 6. Load Initial Data

The project includes an initial data load file that can be used to populate the H2 database. Ensure that the data file is correctly configured and available in the appropriate directory (**[`data.sql`](./infrastructure/src/main/resources/data.sql)**).

### 7. Run the Project

To run the price service, use the following command:

```bash
docker-compose up -d ws-price
```

### 8. Run Performance Tests

To perform performance tests, you can run the following command:

```bash
docker-compose run --rm k6 run /scripts/test.js
```

This will execute the performance tests defined in the `test.js` file.

### 9. Access Metrics in Grafana

After running the performance tests, you can access the metrics in Grafana at the following link:

- **Performance Metrics**:
  [http://localhost:3000/d/Le2Ku9NMk/k6-performance-test?orgId=1](http://localhost:3000/d/Le2Ku9NMk/k6-performance-test?orgId=1)
  ![img_1.png](img/img_1.png)
- **Service Metrics**:
  [http://localhost:3000/d/X034JGT7Gz/app-price-dashboard?orgId=1](http://localhost:3000/d/X034JGT7Gz/app-price-dashboard?orgId=1)

![img.png](img/img.png)

## Explanation of Docker Compose

The `docker-compose.yml` file defines and configures the services necessary for the project's operation. The services that are started include:

- **InfluxDB**: A time-series database used to store performance metrics.

- **Prometheus**: A monitoring and alerting system that collects metrics from the application and other services.

- **Grafana**: A visualization tool that allows creating dashboards from the metrics collected by Prometheus.

- **PostgreSQL**: A relational database used to store persistent data for the SonarQube service.

- **SonarQube**: A tool for code quality analysis and test coverage.

## Accessing the Endpoints

- **Swagger**: The API documentation is available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

- **H2 Console**: You can access the H2 console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console) with the following credentials:
    - JDBC URL: `jdbc:h2:mem:testdb`
    - User: `sa`
    - Password: ``

- **SonarQube**: Access SonarQube at [http://localhost:9000](http://localhost:9000) with the following credentials:
    - User: `admin`
    - Password: `admin` or `admin1` (depending on the initial configuration).

- **Grafana**: The Grafana interface is available at [http://localhost:3000](http://localhost:3000). Anonymous login is enabled by default.

- **Prometheus**: Access Prometheus at [http://localhost:9090](http://localhost:9090) to monitor the service metrics.

## API Endpoints

### Get Preferred Price

- **Method**: `GET`
- **Endpoint**: `/prices`
- **Parameters**:
    - `applicationDate`: Date and time of the price application (format: `yyyy-MM-dd'T'HH:mm:ss`).
    - `productId`: Product ID.
    - `brandId`: Brand ID.

**Example request**:

```bash
curl -X GET "http://localhost:8080/prices?applicationDate=2025-06-14T10:00:00.000&productId=35455&brandId=1"
```

**Response**:

```json
{
  "priceList": 1,
  "productId": 35455,
  "price": 35.50,
  "startDate": "2025-06-14T00:00:00",
  "endDate": "2025-12-31T23:59:59",
  "brandId": 1
}
```

## Test Files

To facilitate endpoint testing, the following files are included:

- **[`test-ws-price.http`](./test-ws-price.http)**: Contains a series of tests for the `/prices` endpoint with different application dates. You can run these tests using an HTTP client that supports this format. Here are some examples of tests included in the file:

  ```http
  ### Test 1
  GET http://localhost:8080/prices?applicationDate=2025-06-14T10:00:00.000&productId=35455&brandId=1

  ### Test 2
  GET http://localhost:8080/prices?applicationDate=2025-06-14T16:00:00.000&productId=35455&brandId=1

  ### Test 3
  GET http://localhost:8080/prices?applicationDate=2025-06-14T21:00:00.000&productId=35455&brandId=1

  ### Test 4
  GET http://localhost:8080/prices?applicationDate=2025-06-15T10:00:00.000&productId=35455&brandId=1

  ### Test 5
  GET http://localhost:8080/prices?applicationDate=2025-06-16T21:00:00.000&productId=35455&brandId=1
  ```

- **[`ws-price.postman_collection.json`](./ws-price.postman_collection.json)**: Postman collection that allows testing the `/prices` endpoint with different application dates. You can import this collection into Postman for easier testing.

## Shared Folder

The `shared` folder contains resources and configurations that are used by different components of the system, especially for monitoring and performance testing. Below is the content and function of each subfolder:

1. **`grafana/`**: This folder contains configurations and dashboards for Grafana, which is a data visualization tool. The files in this folder allow configuring how metrics collected by Prometheus and other systems are presented.
    - **`dashboards/`**: Dashboard configuration files.
        - **`dashboard.yml`**: Defines the dashboard providers that Grafana will use, including configurations for InfluxDB and Prometheus.
        - **`performance-test-dashboard.json`**: A specific dashboard for visualizing performance metrics from tests conducted with k6.
        - **`ws-price-app-dashboard.json`**: A dashboard designed to display specific metrics for the `ws-price` application.
    - **`datasources/`**: Configurations for the data sources that Grafana will use to obtain metrics.
        - **`datasource.yml`**: Defines the data sources, such as InfluxDB and Prometheus, that Grafana will use to collect and display metrics.

2. **`k6/`**: This folder contains scripts for performing performance tests using k6, a load testing and performance testing tool.
    - **`test.js`**: A script that defines different load scenarios to test the `/prices` endpoint. It includes functions to simulate normal requests, requests that do not find the resource, and requests that generate errors.

3. **`prometheus/`**: This folder contains configurations for Prometheus, which is a monitoring and alerting system.
    - **`prometheus.yml`**: A configuration file that defines how and what metrics Prometheus should collect. It includes configurations for the frequency of metric collection and the endpoints it should monitor, such as the `ws-price` application.
