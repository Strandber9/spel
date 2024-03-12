package com.example.spel.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration.RefreshProperties;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.refresh.ConfigDataContextRefresher;
// import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;

@Import(RefreshScope.class)
@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnMissingBean(ContextRefresher.class)
    public ContextRefresher contextRefresher(ConfigurableApplicationContext context, RefreshScope refreshScope){
        return new ConfigDataContextRefresher(context, refreshScope, new RefreshProperties());
    }
    
    @EventListener
    public void handleRefreshScopeRefreshedEvent(RefreshScopeRefreshedEvent event) {
        String refreshedBeanName = event.getName();
        // 이벤트 처리 로직을 여기에 추가
        System.out.println("Bean refreshed: " + refreshedBeanName);
    }

    @EventListener
    public void handleEnvironmentChangeEvent(EnvironmentChangeEvent event) {
        // 이벤트 처리 로직을 여기에 추가
        System.out.println("Bean EnvironmentChangeEvent: ");
    }

}
