/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestDataSourceConfig
 * Author:   JG
 * Date:     2019/3/2 12:09
 * Description: TestDatasource
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.config;

/**
 * 〈一句话功能简述〉<br>
 * 〈TestDatasource〉
 *
 * @author JG
 * @create 2019/3/2
 * @since 1.0.0
 */
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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.vendor.Database;

@Configuration
@EnableTransactionManagement // 开启注解事务管理
@EnableJpaRepositories(entityManagerFactoryRef = "testEntityManagerFactory", // 实体类工厂依赖
        transactionManagerRef = "testTransactionManager", // 事务依赖
        basePackages = "cn.jerome.module.account.dao") // repository类所在的包*/
public class TestDataSourceConfig {
    private Logger log = LoggerFactory.getLogger(TestDataSourceConfig.class);

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("testDataSource")
    private DataSource dataSource;

    /*
     * 通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     */
    @Bean(name = "testEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        log.info("创建testEntityManagerFactoryBean");
        return builder.dataSource(dataSource)// 设置使用的数据源
               /* .properties(jpaProperties.getHibernateProperties(new HibernateSettings())// 设置JPA属性
                .packages("cn.jerome.module.account.entity")// 设置实体类所在的包
                .persistenceUnit("testPersistenceUnit")
                .build();
                .dataSource(dataSource)*/
                .properties(jpaProperties.getHibernateProperties(new HibernateSettings()))
                .packages("cn.jerome.module.account.entity")
                .persistenceUnit("testPersistenceUnit")
                .build();
        // 不要在这里直接获取EntityManagerFactory
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter =new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(false);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return adapter;
    }


    /*
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     */
    @Bean(name = "testEntityManagerFactory")
    @Primary
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
        log.info("创建testEntityManagerFactory");
        return this.entityManagerFactoryBean(builder).getObject();
    }

    /*
     * 配置事务管理器
     */
    @Bean(name = "testTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        log.info("创建testTransactionManager");
        return new JpaTransactionManager(this.entityManagerFactory(builder));
    }

}
