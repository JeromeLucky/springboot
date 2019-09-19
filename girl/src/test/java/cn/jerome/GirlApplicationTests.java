package cn.jerome;

import io.netty.bootstrap.ServerBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class GirlApplicationTests {
	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap serverBootstrap;

	@Test
	public void runWebSocket() {

		try {
			serverBootstrap.bind(9999).sync().channel().closeFuture().sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
