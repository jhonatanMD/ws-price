# Price Service

This service is built with **Spring Boot 3.4+** and **Java 21**.

Is built using a hexagonal architecture that is organized into three main modules:

1. Application: Contains the application logic and use cases. It manages interactions with the domain and serves as the entry point for service operations.

2. Domain: Defines the domain objects and business rules. It is independent of the infrastructure and application, ensuring flexibility and maintainability.

3. Infrastructure: Handles interactions with external systems, such as databases, APIs, network services, etc. It implements the interfaces defined in the application module and provides concrete solutions for persistence and other technical functionalities.

The design follows SOLID principles, ensuring clean, modular, easily extensible, and maintainable code. The separation of layers and the clear definition of responsibilities between modules facilitate testing and system evolution.

This service exposes a RESTful API that allows querying product prices based on certain parameters such as the application date, product ID, and brand ID.

---

## 🚀 Requirements

Make sure you have the following installed on your system:

- Java 21 (JDK)
- Maven
- Docker

---

## ⚙️ How to Set Up the Project

### 1. Clone the repository

```bash
git clone https://github.com/jhonatanMD/ws-price.git
cd ws-price
```


### 2. Start the environment with Docker

```bash
docker-compose build --no-cache  
  ```


 ```bash
  docker-compose up -d influxdb grafana ws-price
   ```
### 3. Test the service at [http://localhost:8080](http://localhost:8080)

Make a GET request to:
```http
http://localhost:8080/prices?applicationDate=2025-06-14T10:00:00.000&productId=35455&brandId=1
```


## Performance Testing
To perform efficiency tests on the service and visualize the results in Grafana, execute the following command:

```bash
docker-compose run --rm k6 run scripts/test.js
  ```

Access the Grafana dashboard at:

http://localhost:3000/d/Le2Ku9NMk/k6-performance-test?orgId=1&from=now-5m&to=now

## ⚡ Service Behavior

This service responds with the price of a specific product and brand based on the provided query date (applicationDate).
The service checks if the requested date falls within the valid date range (from startDate to endDate) for that particular price.
If the query date is within this range, the corresponding price will be returned. Otherwise,
the service may return an error or an empty response.



## 🛠️ Error Handling
Exceptions for invalid requests are handled. It also correctly manages cases when no information is found while consuming external services.