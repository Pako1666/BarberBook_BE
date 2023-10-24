package com.backendapp.barberbook;

import barberbook.mysqlconnection.mysqlconnector.configuration.DBConfiguration;
import barberbook.mysqlconnection.mysqlconnector.configuration.QueriesValidatorConfig;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DBConfiguration.class)

@Getter
@Setter
public class BBConfig {

    @Autowired
    private DBConfiguration dbConfiguration;

}
