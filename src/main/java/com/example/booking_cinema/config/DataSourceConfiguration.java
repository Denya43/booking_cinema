package com.example.booking_cinema.config;

import com.example.booking_cinema.logging.ConnectionLoggingListener;
import com.zaxxer.hikari.HikariDataSource;
import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.proxy.ProxyConfig;
import net.ttddyy.dsproxy.support.ProxyConfigSpringXmlSupport;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource hikariDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public ProxyDataSource dataSource(HikariDataSource hikariDataSource,
                                      ProxyConfig proxyConfig) {
        ProxyDataSource proxyDataSource = new ProxyDataSource(hikariDataSource);
        proxyDataSource.setProxyConfig(proxyConfig);
        return proxyDataSource;
    }

    @Bean
    @Primary
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public ProxyConfigSpringXmlSupport proxyConfigSupport(ChainListener queryListener) {
        ProxyConfigSpringXmlSupport proxyConfigSpringXmlSupport = new ProxyConfigSpringXmlSupport();
        proxyConfigSpringXmlSupport.setDataSourceName("dataSource");
        proxyConfigSpringXmlSupport.setQueryListener(queryListener);
        return proxyConfigSpringXmlSupport;
    }

    @Bean
    public ProxyConfig proxyConfig(ProxyConfigSpringXmlSupport proxyConfigSupport) {
        return proxyConfigSupport.create();
    }

    @Bean
    public ChainListener chainListener(ConnectionLoggingListener connectionLoggingListener) {
        ChainListener chainListener = new ChainListener();
        chainListener.addListener(connectionLoggingListener);
        return chainListener;
    }

    @Bean
    public ConnectionLoggingListener connectionLoggingListener() {
        return new ConnectionLoggingListener(new DefaultQueryLogEntryCreator());
    }

}
