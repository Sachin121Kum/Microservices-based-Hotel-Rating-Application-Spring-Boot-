package com.lwcd.user.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class DatabaseConfig {
    
    // This class can be used to add database-specific configurations
    // if needed in the future
}

