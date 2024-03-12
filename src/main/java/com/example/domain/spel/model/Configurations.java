package com.example.domain.spel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class Configurations {

    private String application;

    private String profile;

    private String label;

    private String propKey;

    private String propValue;

    private String createdAt;

    private String modifiedAt;

}
