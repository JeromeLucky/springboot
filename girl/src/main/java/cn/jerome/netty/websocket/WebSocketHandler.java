/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: WebSocketHandler
 * Author:   JG
 * Date:     2019/9/19 20:52
 * Description: 客户请求业务处理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.netty.websocket;

import com.google.gson.Gson;
import com.rabbitmq.tools.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 〈一句话功能简述〉<br> 
 * 〈客户请求业务处理类〉
 *
 * @author JG
 * @create 2019/9/19
 * @since 1.0.0
 */
@Data
@Component
@Qualifier("webSocketHandler")
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<Object>
{

    private WebSocketServerHandshaker handshaker;

    private static ConcurrentHashMap<String,Map<String,Object>> userChannelMap  = new ConcurrentHashMap<String,Map<String,Object>>();

    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress tcpPort;

    private  static  final String WEB_SOCKET_URL="ws://localhost:8888/websocket";

    private ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //服务端处理客户端的核心方法
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object o) throws Exception {
        //处理客户端向服务端发起http握手请求
        if(o instanceof FullHttpRequest){
            handHttpRequest(ctx,(FullHttpRequest) o);
        }else if(o instanceof WebSocketFrame ){ //处理websocket连接业务
            handlerWebsocketFrame(ctx, (WebSocketFrame) o);
        }
    }

    private  void handlerWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame){
        //判断是否未关闭websocket的请求
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }
        //判断是否为ping消息
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //判断是否为二进制消息
        if(!(frame instanceof TextWebSocketFrame)){
            System.out.println("目前不支持二进制消息");
            throw new RuntimeException("["+this.getClass().getName()+"] 不支持消息");
        }
        //返回应答消息
        //获取客户端向服务端发送的消息
        String request = ((TextWebSocketFrame) frame).text();

        //获取用户信息
        String userId = "";
        for(String key : userChannelMap.keySet()){
            Map channelMap  =  userChannelMap.get(key);
            if(ctx.channel().id().equals(channelMap.get("channelId"))){
                userId = key;
            }
        }
        TextWebSocketFrame tws=new TextWebSocketFrame(new Date().toString()+userId+"====>>"+request);

        //所有连接上的channel 都发送消息
        channels.write(tws);
    }
    /**
     * 功能描述: <br>
     * 〈处理客户端向服务端发起的握手请求的〉
     *

     * @return:
     * @since: 1.0.0
     * @Author:JG
     * @Date: 2019/9/19 21:12
     */
    private  void  handHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req){
        //
        String uri = req.getUri();//连接时配置当前用户  this.websocket = new WebSocket('ws://localhost:8888/websocket？name='+ this.userName)
        String userId = null;
        if (null != uri && uri.contains("/websocket") && uri.contains("?")) {
            String[] uriArray = uri.split("\\?");
            if (null != uriArray && uriArray.length > 1) {
                String[] paramsArray = uriArray[1].split("=");
                if (null != paramsArray && paramsArray.length > 1) {
                   userId = paramsArray[1];
                   Map<String,Object> channelMap =  new HashMap<>();
                        channelMap.put("channelId",ctx.channel().id());
                        channelMap.put("channelObj",ctx.channel());
                    userChannelMap.put(userId,channelMap);
                }
            }
        }
        if(!req.getDecoderResult().isSuccess() || !("websocket".equals(req.headers().get("upgrade")))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //http  websocket 握手请求处理逻辑
        String url="ws://"+tcpPort+"/websocket";
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(url,null,false);
                handshaker=wsFactory.newHandshaker(req);
                if(handshaker==null){
                    WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
                }else{
                    handshaker.handshake(ctx.channel(),req);
                }
         //添加channel

        channels.add(ctx.channel());
        channels.writeAndFlush(new TextWebSocketFrame("[服务器] - "+userId+"加入"));
    }
    /**
     * 功能描述: <br>
     * 〈处理 服务端向客户端的响应信息〉
     *
     [ctx, request]
     * @return:void
     * @since: 1.0.0
     * @Author:JG
     * @Date: 2019/9/19 21:15
     */
    private  void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse resp){

        if(resp.getStatus().code()!=200){
            ByteBuf buffer = Unpooled.copiedBuffer(resp.getStatus().toString(), CharsetUtil.UTF_8);
            resp.content().writeBytes(buffer);
            buffer.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future=ctx.channel().writeAndFlush(resp);
        if(resp.getStatus().code()!=200){
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
    //出现异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       //关闭连接
       ctx.close();
    }
    //服务端读取信息结束时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    //客户端与服务端创建连接时调用
    /*
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       channels.add(ctx.channel())
       channels.writeAndFlush(new TextWebSocketFrame("[服务器] - "+ctx.channel().remoteAddress()+"加入"));
    }

   @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        //广播消息
        channels.writeAndFlush(new TextWebSocketFrame("[服务器] - "+channel.remoteAddress()+"加入"));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //从 map内去除改用户
        channels.writeAndFlush(new TextWebSocketFrame("[服务器] -"+ channel.remoteAddress() +"离开"));
    }
*/

    //客户端与服务端断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());
        String userId = "";
        for(String key : userChannelMap.keySet()){
           Map channelMap  =  userChannelMap.get(key);
           if(ctx.channel().id().equals(channelMap.get("channelId"))){
               userId = key;
           }
        }
        userChannelMap.remove(userId);
        channels.writeAndFlush(new TextWebSocketFrame("[服务器] -"+userId + "下线了"));

    }



    public void sendAllMessage(String message) {
        channels.writeAndFlush(new TextWebSocketFrame(message));
    }

    // 此为单点消息
    public void sendOneMessage(String userId, String message) {
        for(String key : userChannelMap.keySet()){
            if(key.equals(userId)){
                Map channelMap  =  userChannelMap.get(key);
                Channel channel = (Channel) channelMap.get("channelObj");
                channel.writeAndFlush(new TextWebSocketFrame(message));
            }

        }
    }

}
