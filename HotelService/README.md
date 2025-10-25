# Hotel Service

A microservice for managing hotel information in a distributed system architecture.

## Overview

The Hotel Service is responsible for managing hotel data including hotel details, location, and descriptions. It provides RESTful APIs for creating, retrieving, and listing hotels.

## Features

- **Hotel Management**: Create, read, and list hotel information
- **Service Discovery**: Integrated with Eureka for service registration and discovery
- **Database Integration**: Uses PostgreSQL for data persistence
- **RESTful APIs**: Clean REST endpoints for hotel operations
- **Exception Handling**: Global exception handling with custom exceptions

## Technology Stack

- **Framework**: Spring Boot 3.5.6
- **Java Version**: 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **Service Discovery**: Netflix Eureka Client
- **Build Tool**: Maven
- **Lombok**: For reducing boilerplate code

## Architecture

```
HotelService/
├── src/main/java/com/lcwd/hotel/
│   ├── controllers/
│   │   └── HotelController.java          # REST API endpoints
│   ├── entity/
│   │   └── Hotel.java                   # Hotel entity model
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java  # Global exception handling
│   │   └── ResourceNotFoundException.java # Custom exception
│   ├── repositry/
│   │   └── HotelRepositry.java          # Data access layer
│   ├── service/
│   │   ├── HotelService.java            # Service interface
│   │   └── HotelServiceImpl.java        # Service implementation
│   └── HotelServiceApplication.java     # Main application class
└── src/main/resources/
    └── application.yml                   # Configuration
```

## API Endpoints

### Create Hotel
- **POST** `/hotels`
- **Request Body**: 
  ```json
  {
    "name": "Hotel Name",
    "location": "City, Country",
    "about": "Hotel description"
  }
  ```
- **Response**: Created hotel with generated ID

### Get Hotel by ID
- **GET** `/hotels/{hotelId}`
- **Response**: Hotel details or 404 if not found

### Get All Hotels
- **GET** `/hotels`
- **Response**: List of all hotels

## Configuration

### Database Configuration
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hoteldb
    username: postgres
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### Service Discovery
```yaml
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

## Prerequisites

- Java 21
- Maven 3.6+
- PostgreSQL 12+
- Eureka Server running on port 8761

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd HotelService
   ```

2. **Database Setup**
   ```sql
   CREATE DATABASE hoteldb;
   ```

3. **Update database configuration** in `src/main/resources/application.yml`

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

## Running the Service

The service will start on port **8082** by default.

### Health Check
- **URL**: `http://localhost:8082/actuator/health`
- **Status**: Returns service health information

## Database Schema

### Hotels Table
```sql
CREATE TABLE hotels (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    about TEXT
);
```

## Service Integration

This service integrates with:
- **Eureka Server**: For service registration and discovery
- **Other Microservices**: Can be called by UserService for hotel information

## Error Handling

The service includes comprehensive error handling:
- **ResourceNotFoundException**: When hotel is not found
- **GlobalExceptionHandler**: Centralized exception handling
- **HTTP Status Codes**: Proper status codes for different scenarios

## Development

### Project Structure
- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Data access abstraction
- **Entity Layer**: JPA entities for database mapping

### Key Classes
- `HotelController`: REST API endpoints
- `HotelService`: Business logic interface
- `HotelServiceImpl`: Business logic implementation
- `HotelRepositry`: Data access interface
- `Hotel`: JPA entity

## Testing

Run tests using:
```bash
mvn test
```

## Monitoring and Logging

- **Actuator**: Built-in monitoring endpoints
- **Logging**: Configured for development and production
- **Health Checks**: Service health monitoring

## Deployment

The service can be deployed as:
- **Standalone JAR**: `java -jar target/HotelService-0.0.1-SNAPSHOT.jar`
- **Docker Container**: Using Dockerfile
- **Cloud Platforms**: AWS, Azure, GCP

## 👨‍💻 Author

**Sachin Kumar**
- Email: sk31817@gmail.com
- GitHub: [@Sachin121Kum](https://github.com/Sachin121Kum)

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License.
