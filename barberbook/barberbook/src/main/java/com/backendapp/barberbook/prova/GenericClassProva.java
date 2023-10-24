package com.backendapp.barberbook.prova;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.lang.reflect.*;

@Component
@Configuration
@Data
class GenericClassProva<T>{
    public GenericClassProva(){

    }
}