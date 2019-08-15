package com.zikozee.springboot.cruddemo.customHealthCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectorySpaceHealthIndicator extends AbstractHealthIndicator {
    private static final Logger log = LoggerFactory.getLogger(DirectorySpaceHealthIndicator.class);
    private final File directory;
    private final long thresholdBytes;

    @Autowired
    public DirectorySpaceHealthIndicator(@Value("${health.directory.path:${user.dir}}") String path,
                                         @Value("${health.directory.threshold.bytes:5120}") long thresholdBytes){

        directory = new File(path);
        this.thresholdBytes = thresholdBytes;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        log.info("Checking space...");
        long currentDirectorySize = getDirectorySize(directory);

        if (thresholdBytes > currentDirectorySize) {
            log.info("Directory: " + directory.getAbsolutePath() + " looks goood.");
            builder.up().withDetail("directory.name", directory.getAbsolutePath()).withDetail("directory.size", currentDirectorySize);
        } else {
            log.warn("Directory: " + directory.getAbsolutePath() + " has reach the " + thresholdBytes + " bytes threshold.");
            builder.down().withDetail("directory.error", "You reached the threshold of " + thresholdBytes + " bytes from this Directory: " + directory.getAbsolutePath());
        }

    }

    private long getDirectorySize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += getDirectorySize(file);
        }
        return length;
    }

}
