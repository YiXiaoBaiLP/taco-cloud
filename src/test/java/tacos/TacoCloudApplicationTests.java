package tacos;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot测试类
 * @author yixiaobai
 *
 */
//SpringRunner类（子类）是SpringJUnit4ClassRunner的别名（父类）
@RunWith(SpringRunner.class)
@SpringBootTest// 启动Junit测试的时候要添加上SpringBoot的功能
class TacoCloudApplicationTests {

	/**
	 * 测试方法
	 */
	@Test
	void contextLoads() {
	}

}
