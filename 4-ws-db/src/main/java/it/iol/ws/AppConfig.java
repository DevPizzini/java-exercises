package it.iol.ws;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "mydatasource1.datasource")
    public DataSource dataSource1() {
        DataSource ds = DataSourceBuilder.create().build();
        assert (ds != null);
        return ds;
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(@Qualifier("db1") DataSource ds) {
        return new JdbcTemplate(ds);
    }



    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "mydatasource2.datasource")
    public DataSource dataSource2() {
        DataSource ds = DataSourceBuilder.create().build();
        assert (ds != null);
        return ds;
    }

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2(@Qualifier("db2") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
