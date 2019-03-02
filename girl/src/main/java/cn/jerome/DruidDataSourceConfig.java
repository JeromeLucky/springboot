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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
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

   /* @Bean(name = "frameworkDruidDS")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }*/
}
