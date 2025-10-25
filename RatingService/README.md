# Rating Service

A microservice for managing hotel and user ratings in a distributed system architecture.

## Overview

The Rating Service handles user ratings and feedback for hotels. It provides RESTful APIs for creating, retrieving, and querying ratings by user or hotel. This service uses MongoDB for data persistence, making it suitable for handling large volumes of rating data.

## Features

- **Rating Management**: Create and retrieve hotel ratings
- **User-based Queries**: Get all ratings by a specific user
- **Hotel-based Queries**: Get all ratings for a specific hotel
- **Service Discovery**: Integrated with Eureka for service registration
- **NoSQL Database**: Uses MongoDB for flexible data storage
- **RESTful APIs**: Clean REST endpoints for rating operations

## Technology Stack

- **Framework**: Spring Boot 3.5.6
- **Java Version**: 21
- **Database**: MongoDB
- **ORM**: Spring Data MongoDB
- **Service Discovery**: Netflix Eureka Client
- **Build Tool**: Maven
- **Lombok**: For reducing boilerplate code

## Architecture

```
RatingService/
├── src/main/java/com/lwcd/rating/
│   ├── controller/
│   │   └── RatingController.java         # REST API endpoints
│   ├── entity/
│   │   └── Rating.java                  # Rating entity model
│   ├── repositry/
│   │   └── RatingRepo.java              # Data access layer
│   ├── service/
│   │   ├── RatingService.java           # Service interface
│   │   └── RatingImpl.java              # Service implementation
│   └── RatingServiceApplication.java    # Main application class
└── src/main/resources/
    └── application.yml                   # Configuration
```

## API Endpoints

### Create Rating
- **POST** `/ratings`
- **Request Body**: 
  ```json
  {
    "userId": "user123",
    "hotelId": "hotel456",
    "rating": "4.5",
    "feedback": "Great hotel with excellent service"
  }
  ```
- **Response**: Created rating with generated ID

### Get All Ratings
- **GET** `/ratings`
- **Response**: List of all ratings

### Get Ratings by User ID
- **GET** `/ratings/user/{userId}`
- **Response**: List of ratings by specific user

### Get Ratings by Hotel ID
- **GET** `/ratings/hotel/{hotelId}`
- **Response**: List of ratings for specific hotel

## Configuration

### Database Configuration
```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017
      database: ratingdb
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
- MongoDB 4.4+
- Eureka Server running on port 8761

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd RatingService
   ```

2. **Database Setup**
   ```bash
   # Start MongoDB service
   mongod --dbpath /path/to/your/db
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

The service will start on port **8084** by default.

### Health Check
- **URL**: `http://localhost:8084/actuator/health`
- **Status**: Returns service health information

## Database Schema

### Rating Document (MongoDB)
```json
{
  "_id": "ratingId",
  "userId": "user123",
  "hotelId": "hotel456",
  "rating": "4.5",
  "feedback": "Great hotel with excellent service"
}
```

## Service Integration

This service integrates with:
- **Eureka Server**: For service registration and discovery
- **UserService**: Can be called to get user ratings
- **HotelService**: Can be called to get hotel ratings

## Data Model

### Rating Entity
```java
@Document("user_ratings")
public class Rating {
    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private String rating;
    private String feedback;
}
```

## Development

### Project Structure
- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: MongoDB data access
- **Entity Layer**: MongoDB document mapping

### Key Classes
- `RatingController`: REST API endpoints
- `RatingService`: Business logic interface
- `RatingImpl`: Business logic implementation
- `RatingRepo`: MongoDB data access interface
- `Rating`: MongoDB document entity

## MongoDB Queries

The service supports various MongoDB queries:
- **Find by User ID**: `findByUserId(String userId)`
- **Find by Hotel ID**: `findByHotelId(String hotelId)`
- **Find All**: `findAll()`

## Testing

Run tests using:
```bash
mvn test
```

## Monitoring and Logging

- **Actuator**: Built-in monitoring endpoints
- **Logging**: Configured for development and production
- **Health Checks**: Service health monitoring

## Performance Considerations

- **MongoDB Indexing**: Consider adding indexes on `userId` and `hotelId` for better query performance
- **Connection Pooling**: MongoDB connection pooling is handled by Spring Data MongoDB
- **Caching**: Consider implementing caching for frequently accessed ratings

## Deployment

The service can be deployed as:
- **Standalone JAR**: `java -jar target/RatingService-0.0.1-SNAPSHOT.jar`
- **Docker Container**: Using Dockerfile
- **Cloud Platforms**: AWS, Azure, GCP

## Scaling Considerations

- **Horizontal Scaling**: MongoDB supports horizontal scaling with sharding
- **Load Balancing**: Multiple instances can be load balanced
- **Data Partitioning**: Consider partitioning by user or hotel for better performance

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
