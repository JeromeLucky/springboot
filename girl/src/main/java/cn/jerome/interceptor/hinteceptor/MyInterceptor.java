/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyInterceptor
 * Author:   JG
 * Date:     2019/9/9 20:00
 * Description: interceptor
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.interceptor.hinteceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈interceptor〉
 *
 * @author JG
 * @create 2019/9/9
 * @since 1.0.0
 */

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle被调用");
        Map map =(Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        System.out.println(map.get("name"));
        System.out.println(request.getParameter("username"));
        if(map.get("name").equals("zhangsan")) {
            return true;    //如果false，停止流程，api被拦截
        }else {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("please login again!");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}
