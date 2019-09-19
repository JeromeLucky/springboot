/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyFilter
 * Author:   JG
 * Date:     2019/9/9 20:02
 * Description: filter
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.interceptor.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈filter〉
 *
 * @author JG
 * @create 2019/9/9
 * @since 1.0.0
 */

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter " + request.getParameter("username"));
        HttpServletRequest hrequest = (HttpServletRequest)request;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        if(hrequest.getRequestURI().indexOf("/index") != -1 ||
                hrequest.getRequestURI().indexOf("/asd") != -1 ||
                hrequest.getRequestURI().indexOf("/online") != -1 ||
                hrequest.getRequestURI().indexOf("/login") != -1
                ) {
            chain.doFilter(request, response);
        }else {
            wrapper.sendRedirect("/girl/login");
        }
    }

    @Override
    public void destroy() {

    }
}
