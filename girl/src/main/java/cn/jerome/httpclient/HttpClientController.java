/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: HttpClientController
 * Author:   JG
 * Date:     2019/10/19 10:10
 * Description: http cntroller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.httpclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈http cntroller〉
 *
 * @author JG
 * @create 2019/10/19
 * @since 1.0.0
 */
@RestController

public class HttpClientController {
    @Resource
    private HttpAPIService httpAPIService;

    @RequestMapping("httpclient")
    public String test() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");
        System.out.println(str);
        return "hello";
    }
}
