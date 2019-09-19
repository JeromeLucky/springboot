/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MywebConfig
 * Author:   JG
 * Date:     2019/9/9 20:05
 * Description: 配置文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.interceptor.config;

import cn.jerome.interceptor.filter.MyFilter;
import cn.jerome.interceptor.hinteceptor.MyInterceptor;
import cn.jerome.interceptor.listener.MyHttpSessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 〈一句话功能简述〉<br> 
 * 〈配置文件〉
 *
 * @author JG
 * @create 2019/9/9
 * @since 1.0.0
 */
@Configuration
public class MywebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/asd/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/zxc/foo").setViewName("foo");
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
   // @Bean
    public FilterRegistrationBean filterRegist() {
        System.out.println("filter");
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new MyFilter());
        frBean.addUrlPatterns("/*");
        frBean.setOrder(1);
        return frBean;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        System.out.println("listener");
        return srb;
    }
}
