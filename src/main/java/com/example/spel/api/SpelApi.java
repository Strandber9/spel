package com.example.spel.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spel.config.AppConfig;
import com.example.spel.prop.SpelProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SpelApi {

    private final ContextRefresher contextRefresher;

    private final AppConfig appConfig;

    private final SpelProperties props;

    private final ConfigurableApplicationContext context;
    

    @RequestMapping("refresh")
    public Object refresh(){
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources sources = environment.getPropertySources();
        Map<String, Object> poperties = new HashMap<>();
        poperties.put("apps.api.age", (int)(Math.random()*100));
        PropertySource<?> source = new MapPropertySource("spel-props", poperties);

        if (sources.contains("spel-props")){
            sources.replace("spel-props", source);
        } else {
            sources.addFirst(source);
        }

        contextRefresher.refresh();
        return "gg";
    }
    
    @RequestMapping("step-01")
    public Object step01(){
        log.error("{}", props);
        return props.getApi();
    }

}
