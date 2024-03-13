package com.example.domain.spel.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.spel.config.ConfigurationsProperties;
import com.example.domain.spel.config.SpelProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SpelApi {

    private final ContextRefresher contextRefresher;

    private final ConfigurationsProperties props;

    private final ConfigurableApplicationContext applicationContext;

    @RequestMapping("refresh")
    public Object refresh(){
        contextRefresher.refresh();
        return props.getApi();
    }
    
    @RequestMapping("step-01")
    public Object step01(){
        return props.getApi();
    }

    @RequestMapping("step-02")
    public Object step02(@RequestBody Object data){
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        
        context.setRootObject(applicationContext);
        context.setVariable("RequestBody", data);
        context.setVariable("environment", applicationContext.getEnvironment());
        context.setPropertyAccessors(List.of(new ReflectivePropertyAccessor(), new MapAccessor()));
        context.setBeanResolver((EvaluationContext ctx, String beanName) -> {
            try{
                return applicationContext.getBean(beanName);
            } catch(NoSuchBeanDefinitionException e) {
                return context.lookupVariable(beanName);
            }
        });
        return props.getApi().reduce(parser, context);
    }

    @RequestMapping("step-03")
    public Object step03(@RequestBody Object data){
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        
        context.setRootObject(applicationContext);
        context.setVariable("RequestBody", data);
        context.setVariable("environment", applicationContext.getEnvironment());
        context.setPropertyAccessors(List.of(new ReflectivePropertyAccessor(), new MapAccessor()));
        context.setBeanResolver((EvaluationContext ctx, String beanName) -> {
            try{
                return applicationContext.getBean(beanName);
            } catch(NoSuchBeanDefinitionException e) {
                return context.lookupVariable(beanName);
            }
        });

        SpelProperties apiProperty = props.getApi().reduce(parser, context);
        apiProperty.parseExpression(parser, context);

        return apiProperty;
    }

    @RequestMapping("spel")
    public Object spel(@RequestBody Map<String, Object> data){
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setRootObject(applicationContext);
        context.setVariable("RequestBody", data);
        context.setVariable("environment", applicationContext.getEnvironment());
        context.setPropertyAccessors(List.of(new ReflectivePropertyAccessor(), new MapAccessor()));
        context.setBeanResolver((EvaluationContext ctx, String beanName) -> {
            try{
                return applicationContext.getBean(beanName);
            } catch(NoSuchBeanDefinitionException e) {
                return context.lookupVariable(beanName);
            }
        });
        log.error("----------environment: {}", context.lookupVariable("environment"));
        log.error("----------data: {}", data);
        String spel = (String) data.get("spel");
        log.error("--------spel: {}", spel);
        return parser.parseExpression(spel).getValue(context);
    }

}
