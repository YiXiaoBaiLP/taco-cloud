package tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import tacos.web.WebConfig;

@RunWith(SpringRunner.class)
// 此注解会将此类注册到Spring MVC中，这样就可以发送请求了，提供环境支持
//@WebMvcTest(HomeController.class)
@WebMvcTest(WebConfig.class)
public class HomeControllerTest {
	
	// 注入MockMvc
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHomePage() throws Exception {
		// 发起get请求
		mockMvc.perform(get("/"))
		// 期望得到Http 200
		.andExpect(status().isOk())
		// 期望得到视图名称为“home”的视图
		.andExpect(view().name("home"))
		// 期望返回包含“Welocome to...”
		.andExpect(content().string(containsString("Welcome to...")));
	}

}
