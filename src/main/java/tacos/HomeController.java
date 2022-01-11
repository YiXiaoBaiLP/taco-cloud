package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller：表示此类为Spring控制的Bean，控制器
@Controller
public class HomeController {

	// 处理对“/”路径的请求
	@GetMapping("/")
	public String home() {
		// 返回视图名称
		return "home";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}