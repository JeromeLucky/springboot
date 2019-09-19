/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestController
 * Author:   JG
 * Date:     2019/9/9 20:32
 * Description: 测试 过滤器和拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.interceptor.controller;

import cn.jerome.interceptor.listener.MyHttpSessionListener;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试 过滤器和拦截器〉
 *
 * @author JG
 * @create 2019/9/9
 * @since 1.0.0
 */
@RestController
public class TestController {
    @GetMapping("/asd/{name}")
    public String welcome(@PathVariable String name, Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message","hello");
        return "welcome";
    }
    @RequestMapping("/index")
    @ResponseBody
    public Object index(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("zxc", "zxc");
        return  "index";
    }

    @RequestMapping("/online")
    @ResponseBody
    public Object online() {
        return  "当前在线人数：" + MyHttpSessionListener.online + "人";
    }
}
