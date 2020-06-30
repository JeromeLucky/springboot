/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestDom4jDemo
 * Author:   JG
 * Date:     2019/9/11 20:42
 * Description: 测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.dom4j;

import cn.jerome.netty.websocket.WebSocketChannelHandler;
import cn.jerome.netty.websocket.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import technology.tabula.CommandLineApp;

import java.io.File;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试类〉
 *
 * @author JG
 * @create 2019/9/11
 * @since 1.0.0
 */


@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class TestDom4jDemo {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Test
    public void readxml() {
        File file= new File(TestDom4jDemo.class.getResource("/testReadXml.xml").getPath());
        try{
            if(file.exists()){
                Dom4jDemo.readXml(file);
            }else{

                file.createNewFile();
                Dom4jDemo.readXml(file);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void writexml() {
        File file= new File(TestDom4jDemo.class.getResource("/testWriteXml.xml").getPath());
        try{
            if(file.exists()){
                Dom4jDemo.writeXml(file);
            }else{

                file.createNewFile();
                Dom4jDemo.writeXml(file);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @Test
    public  void testPdf(){
/*        String[] args = new String[]{"-f=JSON","-o=d:/output.txt", "-p=all", "D:\\VW481_ZSB_数据三方统计_FK.pdf"};
        CommandLineApp.main(args);*/

        try {
            String[] args = new String[]{"-f=JSON", "-p=all", "C:\\Users\\Jiazh\\Desktop\\VW481_ZSB_数据三方统计_RO6(1).pdf"};
//        CommandLineApp.main(args);
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(CommandLineApp.buildOptions(), args);
            StringBuilder stringBuilder = new StringBuilder();
            new CommandLineApp(stringBuilder, cmd).extractTables(cmd);
            System.out.println("============");
            System.out.println(stringBuilder.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void pushMessage(){
        webSocketHandler.sendAllMessage("11111");
    }
}
