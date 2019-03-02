/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DruidDataSourceConfig
 * Author:   JG
 * Date:     2019/3/1 22:47
 * Description: DruidDataSourceConfig
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome;


import java.sql.SQLException;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 〈一句话功能简述〉<br> 
 * 〈DruidDataSourceConfig 〉
 *
 * @author JG
 * @create 2019/3/1
 * @since 1.0.0
 */
@Configuration
public class DruidDataSourceConfig {

    private Logger log = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;

	/* #####################基础公共配置##################### */

    @Value("${spring.datasource.testdb.url}")
    private String testUrl;

    @Value("${spring.datasource.testdb.username}")
    private String testUsername;

    @Value("${spring.datasource.testdb.password}")
    private String testPassword;

    @Value("${spring.datasource.testdb.driverClassName}")
    private String testDriverClassName;

    @Bean(name = "testDataSource")
    @Primary // 确定主数据源
    public DataSource testDataSource() {
        log.info("创建testDataSource数据源");
        return createDataSource(testUrl, testUsername, testPassword, testDriverClassName);
    }

	/* #####################testDataSource配置##################### */

    @Value("${spring.datasource.demodb.url}")
    private String demoUrl;

    @Value("${spring.datasource.demodb.username}")
    private String demoUsername;

    @Value("${spring.datasource.demodb.password}")
    private String demoPassword;

    @Value("${spring.datasource.demodb.driverClassName}")
    private String demoDriverClassName;

   /* @Bean(name = "demoDataSource")
    public DataSource demoDataSource() {
        log.info("创建demoDataSource数据源");
        return createDataSource(demoUrl, demoUsername, demoPassword, demoDriverClassName);
    }*/

	/* #####################demoDataSource配置##################### */

    private DataSource createDataSource(String url, String username, String password, String driverClassName) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        // configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }

}
