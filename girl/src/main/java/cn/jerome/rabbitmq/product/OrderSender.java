/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderSender
 * Author:   JG
 * Date:     2019/10/6 16:32
 * Description: 订单发送端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.rabbitmq.product;

import cn.jerome.module.order.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈订单发送端〉
 *
 * @author JG
 * @create 2019/10/6
 * @since 1.0.0
 */
@Component
public class OrderSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) throws  Exception{
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange",//交换机 类型 direct topic fanout
                "order.abc",//routing key   topic 的是 可以 指定 order.* 匹配一个单词  order.# 后可以匹配多个单词
                order, //消息体
                correlationData // correlationData 唯一消息id

        );
    }
}
