package com.example.domain.spel.config;

import java.util.Map;

import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.util.StringUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonInclude(value = Include.NON_NULL)
public class SpelProperties {

    @JsonMerge
    @JsonInclude(value = Include.ALWAYS)
    @JsonProperty("mapper-id")
    private String mapperId;

    @JsonMerge
    @JsonProperty("request")
    private BxiMessage request;

    @JsonMerge
    @JsonProperty("response")
    private BxiMessage response;

    @JsonProperty("mappers")
    private Map<String, SpelProperties> mappers;

    @Data
    @JsonInclude(value = Include.NON_NULL)
    public static class BxiMessage {
        @JsonMerge
        @JsonProperty("header")
        private Map<String, Object> header;

        @JsonMerge
        @JsonProperty("data")
        private Map<String, Object> data;

        public void parseExpression(SpelExpressionParser parser, @NonNull StandardEvaluationContext context) {
            /** Request */
            data.entrySet().forEach(entry -> {
                String value = String.class.cast(entry.getValue());
                try{
                    value = parser.parseExpression(value).getValue(context, String.class);
                } catch (SpelEvaluationException e) {} catch(Throwable t){log.error("@@@@@@@@@@: {}", value);}
                data.put(entry.getKey(), value);
            });
            header.entrySet().forEach(entry -> {
                String value = String.class.cast(entry.getValue());
                try{
                    value = parser.parseExpression(value).getValue(context, String.class);
                } catch (SpelEvaluationException e) {} catch(Throwable t){log.error("@@@@@@@@@@: {}", value);}
                header.put(entry.getKey(), value);
            });
        }
    }

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public SpelProperties reduce(SpelExpressionParser parser, @NonNull StandardEvaluationContext context){
        if (StringUtils.isNotBlank(this.mapperId)) {
            log.error("--------mapperId: {}", this.mapperId);
            String mapperId = this.mapperId;
            try {
                mapperId = parser.parseExpression(this.mapperId).getValue(context, String.class);
            } catch(SpelEvaluationException e) {}
            log.error("--------mapperId: {}", mapperId);
            if(this.mappers != null && this.mappers.containsKey(mapperId)){
                SpelProperties updateProps = OBJECT_MAPPER.convertValue(this, this.getClass());
                try {
                    updateProps = OBJECT_MAPPER.readerForUpdating(updateProps).readValue(this.mappers.get(mapperId).toString());
                    log.error("--------updateProps@@: {}", this.mappers.get(mapperId).toString());
                    log.error("--------updateProps: {}", updateProps);
                    return updateProps.reduce(parser, context);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        this.setMappers(null);
        return this;
    }

    public void parseExpression(SpelExpressionParser parser, @NonNull StandardEvaluationContext context){
        this.request.parseExpression(parser, context);
        this.response.parseExpression(parser, context);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
