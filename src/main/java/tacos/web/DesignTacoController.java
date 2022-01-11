package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

@Slf4j // @Slf4j：是一个Lombok所提供的注解，在运行时，他会在这个类中自动生成一个SLF4J(Simple Logging Facade for Java) logger
@Controller // @Controller：控制器，Spring应用上下文的bean
@RequestMapping("/design")// @RequestMapping：注解用到类级别的时候，它能够指定控制器所处理的请求类型
public class DesignTacoController {
	
	/*
	 * @RequestMapping		通用的请求处理
	 * @GetMapping			处理HTTP GET求情
	 * @PostMapping			处理HTTP POST求情
	 * @PutMapping			处理HTTP PUT请求
	 * @DeleteMapping		处理HTTP DELETE请求
	 * @PathMapping			处理HTTP PATCH请求
	 */
	@GetMapping
	// @GetMapping：结合类级别的@RequestMapping来使用，指明当接收到对“/design”的Http请求时，会调用此方法（此注解在Spring 4.3中引入）
	public String showDesignForm(Model model) {
		// 创建Taco的配料表
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jeck", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		// 获取所有的枚举类型，返回一个数组
		Type[] types = Ingredient.Type.values();
		
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), 
					filterByType(ingredients, type));
			
		}
		
		model.addAttribute("design", new Taco());
		// 返回视图的逻辑名称
		return "design";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
		return ingredients.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

}
