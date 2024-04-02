package com.sourcmind.alumni.einvoicing.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        /*Converter<String, UUID> uuidConverter = new AbstractConverter<>() {
            @Override
            protected UUID convert(String source) {
                return UUID.fromString(source);
            }
        };

        modelMapper.addConverter(uuidConverter);*/
        return modelMapper;
    }
}
