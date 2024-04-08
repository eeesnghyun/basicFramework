package com.app.basic.common.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@MapperScan(value = "com.app.basic.domain.**.dao")
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER_CLASS_NAME;
    @Value("${spring.datasource.username}")
    private String USER_NAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
    @Value("${spring.datasource.writer.url}")
    private String WRITER_DATASOURCE_URL;
    @Value("${spring.datasource.reader.url}")
    private String READER_DATASOURCE_URL;

    private final static String WRITER_DATASOURCE = "writerDataSource";
    private final static String READER_DATASOURCE = "readerDataSource";
    // MyBatis xml 설정 파일 경로
    public final static String MYBATIS_CONFIG_LOCATION_PATH = "classpath:mybatis-config.xml";
    // Mapper 경로
    public final static String MAPPER_LOCATIONS_PATH = "classpath:mapper/**/*.xml";

    @Bean
    public DataSource writerDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setJdbcUrl(WRITER_DATASOURCE_URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean
    public DataSource readerDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setJdbcUrl(READER_DATASOURCE_URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean
    @DependsOn({WRITER_DATASOURCE, READER_DATASOURCE})
    public DataSource routingDataSource(
        @Qualifier(WRITER_DATASOURCE) DataSource writer,
        @Qualifier(READER_DATASOURCE) DataSource reader
    ) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("writer", writer);
        targetDataSources.put("reader", reader);

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(writer);

        return routingDataSource;
    }

    @Bean
    public LazyConnectionDataSourceProxy lazyDataSource(RoutingDataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(
        LazyConnectionDataSourceProxy routingDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(
            routingDataSource);
        dataSourceTransactionManager.setNestedTransactionAllowed(true);
        return dataSourceTransactionManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(LazyConnectionDataSourceProxy dataSource)
        throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setConfigLocation(resolver.getResource(MYBATIS_CONFIG_LOCATION_PATH));
        sessionFactory.setMapperLocations(resolver.getResources(MAPPER_LOCATIONS_PATH));

        return sessionFactory.getObject();
    }
}
