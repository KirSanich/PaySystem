package com.example.paysystem.utility;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtility {

    @Bean
    public static ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }
}
