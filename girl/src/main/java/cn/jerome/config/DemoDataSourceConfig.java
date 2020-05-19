/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DemoDataSourceConfig
 * Author:   JG
 * Date:     2019/3/2 12:44
 * Description: DemoDataSourceConfig
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 〈一句话功能简述〉<br> 
 * 〈DemoDataSourceConfig 〉
 *
 * @author JG
 * @create 2019/3/2
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "demoEntityManagerFactory",
        transactionManagerRef = "demoTransactionManager",
        basePackages = "cn.jerome.module.account.dao")
public class DemoDataSourceConfig {

    private Logger log = LoggerFactory.getLogger(DemoDataSourceConfig.class);

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("demoDataSource")
    private DataSource dataSource;

    @Bean(name = "demoEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        log.info("创建demoEntityManagerFactoryBean");
        return builder
                .dataSource(dataSource)
                .properties(jpaProperties.getHibernateProperties(new HibernateSettings()))
                .packages("cn.jerome.module.account.entity")
                .persistenceUnit("demoPersistenceUnit")
                .build();
    }

    @Bean(name = "demoEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder){
        log.info("创建demoEntityManagerFactory");
        return this.entityManagerFactoryBean(builder).getObject();
    }

    @Bean(name = "demoTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
        log.info("创建demoTransactionManager");
        return new JpaTransactionManager(this.entityManagerFactory(builder));
    }

}
