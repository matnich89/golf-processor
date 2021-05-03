package com.mat.golfprocessor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mat.golfprocessor.convertor.Convertor;
import com.mat.golfprocessor.convertor.Source1Convertor;
import com.mat.golfprocessor.convertor.Source2Convertor;
import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource1;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource2;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<Class<? extends GolfData>, Convertor> formatConvertorMap() {
        final Map<Class<? extends GolfData>, Convertor> formatConvertorMap = new HashMap<>();
        formatConvertorMap.put(GolfDataSource1.class, new Source1Convertor());
        formatConvertorMap.put(GolfDataSource2.class, new Source2Convertor());
        return formatConvertorMap;
    }

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.registerModule(module);
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                   .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                   .setFieldMatchingEnabled(true);
        return modelMapper;
    }

}
