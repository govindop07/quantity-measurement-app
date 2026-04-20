package com.qm.measurement.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallback implements UserServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceClientFallback.class);

    @Override
    public boolean validateUser(String email) {
        logger.warn("user-service is unavailable. Falling back for user validation: {}", email);
        // Graceful degradation — assume user is valid if user-service is down
        return true;
    }
}
