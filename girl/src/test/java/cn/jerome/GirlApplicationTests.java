package cn.jerome;

import cn.jerome.httpclient.HttpAPIService;
import cn.jerome.module.order.entity.Order;
import cn.jerome.rabbitmq.product.OrderSender;
import cn.jerome.redis.RedisUtil;
import cn.jerome.sftp.SftpUtil;
import io.netty.bootstrap.ServerBootstrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class GirlApplicationTests {
	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap serverBootstrap;


	@Autowired
	private SftpUtil sftpUtil;

	@Test
	public void runWebSocket() {

		try {
			serverBootstrap.bind(9999).sync().channel().closeFuture().sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private RedisUtil  redisUtil;

	@Test
	public void redistest() {

		redisUtil.set("name","zwd");
		Assert.assertEquals("zwd",redisUtil.get("name"));

	}

	@Autowired
	private  OrderSender orderSender;

	@Test
	public void mqtest() throws  Exception{
		orderSender.send(new Order("121",
				System.currentTimeMillis()+"$"+ UUID.randomUUID().toString(),
				"testOrder"));

	}
	@Autowired
	private HttpAPIService httpAPIService;

	@Test
	public void httptest() throws  Exception {
		String str = httpAPIService.doGet("http://www.baidu.com");
		System.out.println(str);
	}

	@Test
	public void sftpTest(){
		File f = new File("D:\\CP1_B8L_22222_U_202058_report.jpg");
		try {
			FileInputStream fileInputStream = new FileInputStream(f);
			sftpUtil.upload("/home/piWeb/onlineReportPic",f.getName(),fileInputStream);


			byte[] info = sftpUtil.download("/home/piWeb/onlineReportPic",f.getName());
			File file = new File("D:\\a.jpg");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			try {
				fileOutputStream.write(info);
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
