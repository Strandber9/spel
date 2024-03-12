package com.example.domain.spel.api;

import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.spel.config.SpelProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SpelApi {

    private final ContextRefresher contextRefresher;

    private final SpelProperties props;

    private final ConfigurableApplicationContext context;

    @RequestMapping("refresh")
    public Object refresh(){
        contextRefresher.refresh();
        return props.getApi();
    }

    @RequestMapping("env")
    public Object environment(){
        ConfigurableEnvironment environment = context.getEnvironment();
        return environment.getPropertySources();
    }
    
    @RequestMapping("step-01")
    public Object step01(){
        log.error("{}", props);
        return props.getApi();
    }

}
