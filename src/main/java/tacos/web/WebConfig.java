package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 一个简单的视图 控制器；可以替换HomeController类
 * @author yixiaobai
 * @Version 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	/**
	 * 注册一个或多个视图控制器
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 将“/”传递进去，视图控制器将会针对改路径执行Get请求；将请求转发到“home”视图上
		registry.addViewController("/").setViewName("home");
	}

}
