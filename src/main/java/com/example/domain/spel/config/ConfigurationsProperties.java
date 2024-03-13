package com.example.domain.spel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@RefreshScope
@Component
@ConfigurationProperties(prefix = "apps")
public class ConfigurationsProperties {
    private SpelProperties api;
}
