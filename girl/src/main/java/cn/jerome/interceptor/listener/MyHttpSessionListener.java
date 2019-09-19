/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyHttpSessionListener
 * Author:   JG
 * Date:     2019/9/9 19:58
 * Description: listener
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.interceptor.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 〈一句话功能简述〉<br> 
 * 〈listener〉
 *
 * @author JG
 * @create 2019/9/9
 * @since 1.0.0
 */

public class MyHttpSessionListener implements HttpSessionListener {
    public static int online = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        online ++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
    }
}
