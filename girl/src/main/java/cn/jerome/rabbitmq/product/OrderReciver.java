/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderReciver
 * Author:   JG
 * Date:     2019/10/6 18:10
 * Description: 消息接收
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.rabbitmq.product;

import cn.jerome.module.order.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消息接收〉
 *
 * @author JG
 * @create 2019/10/6
 * @since 1.0.0
 */
@Component
public class OrderReciver {
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "order-queue",durable = "true"),
                    exchange = @Exchange(value = "order-exchange",durable = "true",type = "topic"),
                    key = "order.*"
            )
    )
    @RabbitHandler //标识方法
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String,Object> headers,
                               Channel channel) throws  Exception{
        //消费者操作
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("订单Id:"+order.getOrderId());
        channel.basicAck(deliveryTag,false); //手动签收 DELIVERY_TAG  批量操作flage
    }
}
