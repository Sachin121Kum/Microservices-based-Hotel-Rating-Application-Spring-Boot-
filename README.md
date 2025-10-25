# Microservices Architecture Project

A comprehensive microservices-based hotel management system built with Spring Boot, featuring service discovery, inter-service communication, and distributed data management.

## 🏗️ Architecture Overview

This project implements a distributed microservices architecture with the following components:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   User Service  │    │  Hotel Service  │    │ Rating Service   │
│   (Port: 8081)  │    │   (Port: 8082)  │    │   (Port: 8084)   │
│     MySQL       │    │   PostgreSQL    │    │    MongoDB       │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │ Service Registry │
                    │   (Port: 8761)   │
                    │   Eureka Server  │
                    └─────────────────┘
```

## 🚀 Services

### 1. Service Registry (Eureka Server)
- **Port**: 8761
- **Purpose**: Service discovery and registration
- **Technology**: Spring Cloud Netflix Eureka
- **Dashboard**: http://localhost:8761

### 2. User Service
- **Port**: 8081
- **Database**: MySQL
- **Purpose**: User management and service orchestration
- **Features**: User CRUD operations, service integration
- **Documentation**: [UserService/README.md](UserService/README.md)

### 3. Hotel Service
- **Port**: 8082
- **Database**: PostgreSQL
- **Purpose**: Hotel information management
- **Features**: Hotel CRUD operations
- **Documentation**: [HotelService/README.md](HotelService/README.md)

### 4. Rating Service
- **Port**: 8084
- **Database**: MongoDB
- **Purpose**: Rating and feedback management
- **Features**: Rating CRUD operations, user/hotel-based queries
- **Documentation**: [RatingService/README.md](RatingService/README.md)

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.4.0 - 3.5.6
- **Java Version**: 21-24
- **Service Discovery**: Netflix Eureka
- **Service Communication**: OpenFeign
- **Databases**: MySQL, PostgreSQL, MongoDB
- **Build Tool**: Maven
- **ORM**: Spring Data JPA, Spring Data MongoDB

## 📋 Prerequisites

- Java 21+ (Java 24 for UserService)
- Maven 3.6+
- MySQL 8.0+
- PostgreSQL 12+
- MongoDB 4.4+

## 🚀 Quick Start

### 1. Start the Service Registry
```bash
cd ServiceRegistry
mvn spring-boot:run
```
**Dashboard**: http://localhost:8761

### 2. Start the Hotel Service
```bash
cd HotelService
mvn spring-boot:run
```
**API**: http://localhost:8082

### 3. Start the Rating Service
```bash
cd RatingService
mvn spring-boot:run
```
**API**: http://localhost:8084

### 4. Start the User Service
```bash
cd UserService
mvn spring-boot:run
```
**API**: http://localhost:8081

## 🗄️ Database Setup

### MySQL (User Service)
```sql
CREATE DATABASE microservices;
```

### PostgreSQL (Hotel Service)
```sql
CREATE DATABASE hoteldb;
```

### MongoDB (Rating Service)
```bash
# Start MongoDB
mongod --dbpath /path/to/your/db
```

## 🔧 Configuration

### Service Discovery
All services are configured to register with Eureka:
```yaml
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

### Database Connections
Each service has its own database configuration:
- **UserService**: MySQL on port 3309
- **HotelService**: PostgreSQL on port 5432
- **RatingService**: MongoDB on port 27017

## 📡 API Endpoints

### User Service (Port 8081)
- `POST /user` - Create user
- `GET /user/{userId}` - Get user by ID
- `GET /user` - Get all users

### Hotel Service (Port 8082)
- `POST /hotels` - Create hotel
- `GET /hotels/{hotelId}` - Get hotel by ID
- `GET /hotels` - Get all hotels

### Rating Service (Port 8084)
- `POST /ratings` - Create rating
- `GET /ratings` - Get all ratings
- `GET /ratings/user/{userId}` - Get ratings by user
- `GET /ratings/hotel/{hotelId}` - Get ratings by hotel

## 🔄 Service Communication

### Inter-Service Communication
- **UserService** → **HotelService**: Via OpenFeign
- **UserService** → **RatingService**: Via OpenFeign
- **Service Discovery**: All services register with Eureka

### Communication Flow
1. UserService receives request
2. Queries RatingService for user ratings
3. Queries HotelService for hotel details
4. Aggregates and returns comprehensive user information

## 🏗️ Project Structure

```
microservice/
├── ServiceRegistry/          # Eureka Server
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── UserService/              # User Management
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── HotelService/             # Hotel Management
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── RatingService/            # Rating Management
│   ├── src/
│   ├── pom.xml
│   └── README.md
└── README.md                 # This file
```

## 🧪 Testing

### Individual Service Testing
```bash
# Test each service
cd ServiceRegistry && mvn test
cd UserService && mvn test
cd HotelService && mvn test
cd RatingService && mvn test
```

### Integration Testing
1. Start all services
2. Use Postman or curl to test API endpoints
3. Verify service registration in Eureka dashboard

## 📊 Monitoring

### Health Checks
- **UserService**: http://localhost:8081/actuator/health
- **HotelService**: http://localhost:8082/actuator/health
- **RatingService**: http://localhost:8084/actuator/health

### Service Discovery Dashboard
- **Eureka Dashboard**: http://localhost:8761

## 🚀 Deployment

### Local Development
1. Start databases (MySQL, PostgreSQL, MongoDB)
2. Start Service Registry
3. Start all microservices
4. Access services via their respective ports

### Production Considerations
- **Load Balancing**: Multiple instances of each service
- **High Availability**: Eureka server clustering
- **Database Scaling**: Read replicas, connection pooling
- **Monitoring**: Comprehensive logging and metrics

## 🔒 Security Considerations

- **Input Validation**: Validate all input data
- **Authentication**: Implement JWT or OAuth2
- **Authorization**: Role-based access control
- **HTTPS**: Use HTTPS in production
- **Database Security**: Secure database connections

## 📈 Performance Optimization

- **Caching**: Implement Redis for caching
- **Connection Pooling**: Optimize database connections
- **Load Balancing**: Distribute load across instances
- **Circuit Breaker**: Implement circuit breaker pattern
- **Async Processing**: Use async processing where appropriate

## 🐳 Docker Support

Each service can be containerized:
```dockerfile
# Example Dockerfile for UserService
FROM openjdk:21-jdk-slim
COPY target/UserService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🔧 Development

### Adding New Services
1. Create new Spring Boot project
2. Add Eureka client dependency
3. Configure service registration
4. Implement business logic
5. Add to service discovery

### Modifying Existing Services
1. Update service logic
2. Test thoroughly
3. Update documentation
4. Deploy with zero downtime

## 📚 Documentation

- [UserService Documentation](UserService/README.md)
- [HotelService Documentation](HotelService/README.md)
- [RatingService Documentation](RatingService/README.md)
- [ServiceRegistry Documentation](ServiceRegistry/README.md)

## 👨‍💻 Author

**Sachin Kumar**
- Email: sk31817@gmail.com
- GitHub: [@Sachin121Kum](https://github.com/Sachin121Kum)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Update documentation
6. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions:
- Check individual service documentation
- Review logs for error messages
- Verify service registration in Eureka dashboard
- Ensure all databases are running

## 🔮 Future Enhancements

- **API Gateway**: Implement Spring Cloud Gateway
- **Config Server**: Centralized configuration management
- **Circuit Breaker**: Implement resilience patterns
- **Distributed Tracing**: Add Zipkin or Jaeger
- **Message Queues**: Implement event-driven architecture
- **Kubernetes**: Container orchestration
- **Monitoring**: Prometheus and Grafana integration
