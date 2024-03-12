package com.example.domain.spel.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.spel.model.Configurations;

@Mapper
public interface ConfigurationsMapper {
    
    List<Configurations> findByProfile(@Param("profile") String profile);

}
