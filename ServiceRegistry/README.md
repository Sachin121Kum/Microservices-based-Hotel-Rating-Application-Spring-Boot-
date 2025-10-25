# Service Registry (Eureka Server)

A service registry and discovery server using Netflix Eureka for managing microservices in a distributed system architecture.

## Overview

The Service Registry is the central component that enables service discovery and registration in the microservices ecosystem. It acts as a phone book for microservices, allowing them to find and communicate with each other without hardcoded URLs.

## Features

- **Service Registration**: Microservices register themselves with the registry
- **Service Discovery**: Microservices can discover other services
- **Health Monitoring**: Monitors the health of registered services
- **Load Balancing**: Supports client-side load balancing
- **High Availability**: Can be configured for high availability
- **Dashboard**: Web-based dashboard for monitoring services

## Technology Stack

- **Framework**: Spring Boot 3.5.6
- **Java Version**: 21
- **Service Registry**: Netflix Eureka Server
- **Build Tool**: Maven
- **Cloud**: Spring Cloud

## Architecture

```
ServiceRegistry/
├── src/main/java/com/lwcd/serviceRegistry/
│   └── ServiceRegistryApplication.java  # Main application class
└── src/main/resources/
    └── application.yml                   # Configuration
```

## Configuration

### Eureka Server Configuration
```yaml
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
```

## Prerequisites

- Java 21
- Maven 3.6+

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ServiceRegistry
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

## Running the Service

The service will start on port **8761** by default.

### Eureka Dashboard
- **URL**: `http://localhost:8761`
- **Features**: 
  - View registered services
  - Monitor service health
  - Service instances management

## Service Registration

### How Services Register
1. Services start up and register with Eureka
2. Eureka stores service information
3. Services send heartbeats to maintain registration
4. Eureka removes services that don't send heartbeats

### Registration Process
```yaml
# In microservice application.yml
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

## Service Discovery

### How Services Discover Each Other
1. Services query Eureka for service locations
2. Eureka returns available instances
3. Services can choose from available instances
4. Load balancing can be applied

## Dashboard Features

### Eureka Dashboard
- **Instances**: View all registered service instances
- **Status**: Monitor service health status
- **Metadata**: View service metadata
- **Actions**: Start/stop service instances

### Service Information
- **Service Name**: Registered service name
- **Instance ID**: Unique instance identifier
- **Status**: UP, DOWN, OUT_OF_SERVICE
- **Metadata**: Custom service metadata

## High Availability

### Standalone Mode
- Single Eureka server instance
- Suitable for development and testing
- Simple configuration

### High Availability Mode
- Multiple Eureka server instances
- Peer-to-peer replication
- Suitable for production

### HA Configuration
```yaml
# Eureka Server 1
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka-server-2:8761/eureka/

# Eureka Server 2
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka-server-1:8761/eureka/
```

## Security

### Basic Authentication
```yaml
eureka:
  client:
    service-url:
      defaultZone: http://username:password@localhost:8761/eureka/
```

### HTTPS Configuration
```yaml
eureka:
  client:
    service-url:
      defaultZone: https://localhost:8761/eureka/
```

## Monitoring and Health Checks

### Health Endpoints
- **Health Check**: `/actuator/health`
- **Info**: `/actuator/info`
- **Metrics**: `/actuator/metrics`

### Service Health
- **Heartbeat**: Services send heartbeats every 30 seconds
- **Timeout**: Services are removed after 90 seconds of no heartbeat
- **Renewal**: Services renew registration every 30 seconds

## Configuration Options

### Eureka Server Configuration
```yaml
eureka:
  server:
    enable-self-preservation: false  # Disable self-preservation mode
    eviction-interval-timer-in-ms: 5000  # Eviction interval
  instance:
    lease-renewal-interval-in-seconds: 30  # Heartbeat interval
    lease-expiration-duration-in-seconds: 90  # Timeout duration
```

### Client Configuration
```yaml
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${spring.application.instance_id:${random.value}}
```

## Troubleshooting

### Common Issues
1. **Service Not Registering**: Check network connectivity and configuration
2. **Service Not Discoverable**: Verify service registration and discovery settings
3. **Dashboard Not Accessible**: Check port configuration and firewall settings

### Debugging
- **Logs**: Check application logs for errors
- **Dashboard**: Use Eureka dashboard to monitor services
- **Health Checks**: Verify service health endpoints

## Production Considerations

### Performance
- **Memory**: Eureka server memory requirements
- **Network**: Network latency considerations
- **Scaling**: Horizontal scaling strategies

### Reliability
- **Backup**: Regular backup of Eureka data
- **Monitoring**: Comprehensive monitoring setup
- **Alerting**: Alert on service failures

## Integration with Other Services

### Spring Cloud Services
- **Config Server**: Service configuration
- **Gateway**: API Gateway integration
- **Circuit Breaker**: Hystrix integration

### Third-party Services
- **Consul**: Alternative service registry
- **Zookeeper**: Alternative service registry
- **Kubernetes**: Container orchestration

## Deployment

The service can be deployed as:
- **Standalone JAR**: `java -jar target/ServiceRegistry-0.0.1-SNAPSHOT.jar`
- **Docker Container**: Using Dockerfile
- **Cloud Platforms**: AWS, Azure, GCP
- **Kubernetes**: Container orchestration

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
