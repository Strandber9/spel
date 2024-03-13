package com.example.global.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration.RefreshProperties;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.refresh.ConfigDataContextRefresher;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import com.example.domain.spel.mappers.ConfigurationsMapper;
import com.example.domain.spel.model.Configurations;

@Import(RefreshScope.class)
@Configuration
public class AppConfig {

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private ConfigurationsMapper configurationsMapper;

    @Bean
    @ConditionalOnMissingBean(ContextRefresher.class)
    public ContextRefresher contextRefresher(ConfigurableApplicationContext context, RefreshScope refreshScope) {
        return new ConfigDataContextRefresher(context, refreshScope, new RefreshProperties());
    }

    @EventListener
    public void handleRefreshScopeRefreshedEvent(RefreshScopeRefreshedEvent event) {
        String refreshedBeanName = event.getName();
        System.out.println("Bean refreshed: " + refreshedBeanName);
    }

    @EventListener
    public void handleEnvironmentChangeEvent(EnvironmentChangeEvent event) {
        System.out.println("Bean EnvironmentChangeEvent: ");
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources sources = environment.getPropertySources();

        List<Configurations> configurations = configurationsMapper.findByProfile("dev");
        Map<String, Object> poperties = configurations.stream()
                .collect(Collectors.toMap(Configurations::getPropKey, Configurations::getPropValue,
                        (existingValue, newValue) -> newValue, LinkedHashMap::new));
        PropertySource<?> source = new MapPropertySource("spel-props", poperties);

        if (sources.contains("spel-props")) {
            sources.replace("spel-props", source);
        } else {
            sources.addFirst(source);
        }
    }

}
