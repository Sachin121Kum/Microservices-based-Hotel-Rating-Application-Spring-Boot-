# User Service

A comprehensive microservice for managing user information and integrating with other services in a distributed system architecture.

## Overview

The User Service is the central service for managing user data and orchestrating interactions with other microservices. It provides RESTful APIs for user management and integrates with HotelService and RatingService to provide comprehensive user information including ratings and hotel details.

## Features

- **User Management**: Create, read, and list user information
- **Service Integration**: Integrates with HotelService and RatingService using OpenFeign
- **Service Discovery**: Integrated with Eureka for service registration and discovery
- **Database Integration**: Uses MySQL for data persistence
- **RESTful APIs**: Clean REST endpoints for user operations
- **Microservice Communication**: Inter-service communication using Feign clients

## Technology Stack

- **Framework**: Spring Boot 3.4.0
- **Java Version**: 24
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Service Discovery**: Netflix Eureka Client
- **Service Communication**: OpenFeign
- **Build Tool**: Maven
- **Lombok**: For reducing boilerplate code
- **Actuator**: For monitoring and health checks

## Architecture

```
UserService/
├── src/main/java/com/lwcd/user/service/
│   ├── controller/
│   │   └── UserController.java           # REST API endpoints
│   ├── entity/
│   │   ├── User.java                     # User entity model
│   │   ├── Hotel.java                    # Hotel entity (for external service)
│   │   └── Rating.java                   # Rating entity (for external service)
│   ├── external/
│   │   └── services/
│   │       └── HotelService.java         # Feign client for HotelService
│   ├── service/
│   │   ├── UserService.java              # Service interface
│   │   └── UserServiceImpl.java          # Service implementation
│   ├── repositories/
│   │   └── UserRepository.java           # Data access layer
│   ├── config/
│   │   ├── DatabaseConfig.java           # Database configuration
│   │   └── MyConfig.java                 # Custom configuration
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java   # Global exception handling
│   │   └── ResourceNotFoundException.java # Custom exception
│   ├── payload/
│   │   └── ApiResponse.java              # API response wrapper
│   └── UserServiceApplication.java       # Main application class
└── src/main/resources/
    └── application.yml                    # Configuration
```

## API Endpoints

### Create User
- **POST** `/user`
- **Request Body**: 
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "about": "Software Developer"
  }
  ```
- **Response**: Created user with generated ID

### Get User by ID
- **GET** `/user/{userId}`
- **Response**: User details with associated ratings and hotel information

### Get All Users
- **GET** `/user`
- **Response**: List of all users

## Configuration

### Database Configuration
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3309/microservices
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
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

- Java 24
- Maven 3.6+
- MySQL 8.0+
- Eureka Server running on port 8761
- HotelService running on port 8082
- RatingService running on port 8084

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd UserService
   ```

2. **Database Setup**
   ```sql
   CREATE DATABASE microservices;
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

The service will start on port **8081** by default.

### Health Check
- **URL**: `http://localhost:8081/actuator/health`
- **Status**: Returns service health information

## Database Schema

### Users Table
```sql
CREATE TABLE micro_users (
    ID VARCHAR(255) PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255) UNIQUE,
    ABOUT TEXT
);
```

## Service Integration

This service integrates with:
- **Eureka Server**: For service registration and discovery
- **HotelService**: Via OpenFeign client for hotel information
- **RatingService**: Via OpenFeign client for rating information

### Feign Client Configuration
```java
@FeignClient(name="HOTELSERVICE")
public interface HotelService {
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
```

## Data Model

### User Entity
```java
@Entity
@Table(name="micro_users")
public class User {
    @Id
    @Column(name="ID")
    private String userId;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="ABOUT")
    private String about;
    
    @Transient
    private List<Rating> ratings;
}
```

## Microservice Communication

The UserService communicates with other services using:
- **OpenFeign**: For declarative REST client
- **Service Discovery**: Eureka for service location
- **Load Balancing**: Built-in with Feign and Eureka

## Development

### Project Structure
- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic and service orchestration
- **Repository Layer**: Data access abstraction
- **Entity Layer**: JPA entities for database mapping
- **External Services**: Feign clients for inter-service communication

### Key Classes
- `UserController`: REST API endpoints
- `UserService`: Business logic interface
- `UserServiceImpl`: Business logic implementation
- `UserRepository`: Data access interface
- `HotelService`: Feign client for hotel service
- `User`: JPA entity

## Error Handling

The service includes comprehensive error handling:
- **ResourceNotFoundException**: When user is not found
- **GlobalExceptionHandler**: Centralized exception handling
- **HTTP Status Codes**: Proper status codes for different scenarios
- **Service Communication Errors**: Handling of external service failures

## Testing

Run tests using:
```bash
mvn test
```

## Monitoring and Logging

- **Actuator**: Built-in monitoring endpoints
- **Logging**: Configured for development and production
- **Health Checks**: Service health monitoring
- **Metrics**: Application metrics and performance monitoring

## Performance Considerations

- **Connection Pooling**: HikariCP for database connection pooling
- **Caching**: Consider implementing caching for frequently accessed data
- **Circuit Breaker**: Consider implementing circuit breaker pattern for external service calls
- **Timeout Configuration**: Configure appropriate timeouts for external service calls

## Deployment

The service can be deployed as:
- **Standalone JAR**: `java -jar target/UserService-0.0.1-SNAPSHOT.jar`
- **Docker Container**: Using Dockerfile
- **Cloud Platforms**: AWS, Azure, GCP

## Scaling Considerations

- **Horizontal Scaling**: Multiple instances can be load balanced
- **Database Scaling**: Consider read replicas for better performance
- **Service Communication**: Feign clients support load balancing
- **Caching**: Implement caching for better performance

## Security Considerations

- **Input Validation**: Validate all input data
- **SQL Injection**: Use parameterized queries
- **Authentication**: Consider implementing authentication
- **Authorization**: Implement proper authorization mechanisms

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
