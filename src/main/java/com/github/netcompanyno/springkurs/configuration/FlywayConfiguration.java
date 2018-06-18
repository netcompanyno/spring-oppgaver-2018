package com.github.netcompanyno.springkurs.configuration;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {
    
    @Bean
    @Autowired
    public Flyway flyway(final DataSource dataSource) {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
        return flyway;
    }

}
