package com.zikozee.springboot.cruddemo.customHealthCheck;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
@Component
public class MyHealthIndicator implements HealthIndicator {

    private Logger LOG = LoggerFactory.getLogger(MyHealthIndicator.class);

    @Autowired
    HikariDataSource customerDataSource;

    @Override
    public Health health() {

        // check db available
        try (Connection connection = customerDataSource.getConnection()) {

        } catch (SQLException e) {
            LOG.warn("DB not available");
            return Health.down().withDetail("smoke test", e.getMessage()).build();
        }

        // check some service url is reachable
        String resUrl = "https://www.otakusream.tv";
        try {
            URL url = new URL(resUrl);
            int port = url.getPort();
            if (port == -1) {
                port = url.getDefaultPort();
            }

            try (Socket socket = new Socket(url.getHost(), port)) {
            } catch (IOException e) {
                LOG.warn("Failed to open socket to " + resUrl);
                return Health.down().withDetail("smoke test", e.getMessage()).build();
            }
        } catch (MalformedURLException e1) {
            LOG.warn("Malformed URL: " + resUrl);
            return Health.down().withDetail("smoke test", e1.getMessage()).build();
        }

        return Health.up().build();
    }

}