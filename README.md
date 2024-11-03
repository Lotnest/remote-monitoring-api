# Remote Monitoring and Alerting System for Applications

This project provides a backend system for monitoring the health of various applications. It leverages MongoDB for data storage and Spring Boot for the application framework, utilizing reactive programming principles for efficient health check operations.

## Features

- **Application Monitoring**: Track the status of applications (UP/DOWN) based on HTTP response checks.
- **Reactive Programming**: Utilizes Spring WebFlux for non-blocking operations.
- **MongoDB Integration**: Stores application data and status updates in MongoDB.
- **Health Checks**: Periodic health checks are performed on all active applications.
- **Docker Support**: Easily deploy the application along with MongoDB using Docker.

## Prerequisites

- Java 21 or higher
- Docker (optional, for running with Docker)
- MongoDB (optional, if not using Docker)

## Installation

### Clone the Repository

```bash
git clone https://github.com/Lotnest/remote-monitoring-api.git
cd remote-monitoring-api
```

### Build the Project

Make sure you have [Gradle](https://gradle.org/install/) installed. Then, run the following command to build the project:

```bash
./gradlew build
```

### Configure MongoDB

If you want to run MongoDB locally, make sure it is running on the default port (27017). You can also use the provided Docker configuration.

### Docker Setup

To run the application and MongoDB using Docker, follow these steps:

1. Navigate to the project root directory.
2. Run the following command to start both services:

```bash
docker-compose up
```

This command will build the application Docker image and start a MongoDB container.

## Configuration

The application configuration can be found in the `src/main/resources/application.yaml` file. You can modify the MongoDB connection URI or any other settings as necessary.

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://mongodb:27017/remote-monitoring
management:
  endpoints:
    web:
      exposure:
        include: health
  endpoint:
    health:
      show-details: always
```

## Running the Application

Once everything is set up, you can run the Spring Boot application:

```bash
./gradlew bootRun
```

The application will start on [http://localhost:8080](http://localhost:8080).

## Usage

You can interact with the application through the REST API. The main endpoint for managing applications is `/applications`. You can send POST requests to create new applications and track their health.

### Example Request

To create a new application, you can use the following JSON body:

```json
{
  "name": "Example Application",
  "url": "http://example.com",
  "responseTimeThreshold": 2000,
  "monitoringActive": true,
  "type": "WEB",
  "environment": "PRODUCTION",
  "ownerEmail": "owner@example.com",
  "tags": ["example", "monitoring"]
}
```

### Health Checks

The application will perform health checks every minute on all active applications and log their status. You can view the logs to see the results of these checks.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
