package com.zikozee.springboot.cruddemo.customHealthCheck;

import com.zikozee.springboot.cruddemo.Service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServiceHealthCheck implements HealthIndicator {

    private static final Logger log = LoggerFactory.getLogger(ServiceHealthCheck.class);

    @Autowired
    SimpleService simpleService;

    @Override
    public Health health() {
        Code code = simpleService.getStatus();
        log.info("Checking up service...");
        log.info("Service status code:" + code);
        switch(code){
            case OK:
                return Health.up().withDetail("service.code", code).build();
            case UNREACHABLE:
                return Health.status("UNREACHABLE").build();
            default:
                return Health.outOfService().withDetail("service.code",code).build();
        }
    }
}