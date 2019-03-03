/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: JpaProperties
 * Author:   JG
 * Date:     2019/3/3 14:53
 * Description: JpaProperties
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 〈一句话功能简述〉<br> 
 * 〈JpaProperties〉
 *
 * @author JG
 * @create 2019/3/3
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "spring.jpa")
public class JpaProperties {
}
