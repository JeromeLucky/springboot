/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Order
 * Author:   JG
 * Date:     2019/10/6 16:25
 * Description: 记录消息订单
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.module.order.entity;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈记录消息订单〉
 *
 * @author JG
 * @create 2019/10/6
 * @since 1.0.0
 */

public class Order implements Serializable
{

    private  String orderId;
    private  String messageId;
    private  String name;

    public Order() {
    }

    public Order(String orderId, String messageId, String name) {
        this.orderId = orderId;
        this.messageId = messageId;
        this.name = name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
