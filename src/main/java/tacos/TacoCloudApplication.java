package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication：是一个组合注解，它组合了3个其他的注解
 * 		@SpringBootConfiguration：将该类声明为配置类。（是@Configuration注解的特殊形式）。
 * 		@EnableAutoConfiguration：启用Spring Boot的自动配置。
 * 		@ComponentScan：启用组件扫描。（可以发现@Component、@Controller、@Service这样的注解）
 */
// 此注解表示Spring Boot的入口
@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		// 运行程序的入口
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
