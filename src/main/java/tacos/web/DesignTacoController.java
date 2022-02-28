package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Slf4j // @Slf4j：是一个Lombok所提供的注解，在运行时，他会在这个类中自动生成一个SLF4J(Simple Logging Facade for Java) logger
@Controller // @Controller：控制器，Spring应用上下文的bean
@RequestMapping("/design")// @RequestMapping：注解用到类级别的时候，它能够指定控制器所处理的请求类型
@SessionAttributes("order")// @SessionAttributes: 可以使对象跨页面使用，也就是将对象保存到session中去
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	/** 注入并使用TacoRepository*/
	// 可以在showDesignForm()、processDesign()方法中使用了
	private TacoRepository designRepo;
	
	//自动注入的构造函数中
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo,
			TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}
	
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
	public String showDesignForm(Model model/* Model参数：负责控制器和展现数据的视图之间传递数据*/) {
											/* 放到Model属性中的数据将会复制到Servlet Response的属性中*/
		// 创建Taco的配料表
//		List<Ingredient> ingredients = Arrays.asList(
//				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//				new Ingredient("CHED", "Cheddar", Type.CHEESE),
//				new Ingredient("JACK", "Monterrey Jeck", Type.CHEESE),
//				new Ingredient("SLSA", "Salsa", Type.SAUCE),
//				new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		
		List<Ingredient> ingredients = new ArrayList<>();
		// 将查询到的配料信息保存到ingredients集合中
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		// 获取所有的枚举类型，返回一个数组
		Type[] types = Ingredient.Type.values();
		
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), 
					filterByType(ingredients, type));
		}
		// 将Taco对象存放到design属性中
		model.addAttribute("design", new Taco());
		// 返回视图的逻辑名称
		return "design";
	}
	/*
	 * @ModelAttribute：会在控制器请求方法执行前执行，这里每次请求“/design"时都会构建一个
	 * order和taco属性，其值分别为空对象。
	 * 其中order（@SessionAttributes("order")）属性会提升到session域中
	 */
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	/**
	 * 保存taco设计并将他们链接至订单页面
	 * @param design
	 * @param errors
	 * @return
	 */
	@PostMapping
	// @Valid:此注解会告诉Spring MVC要对提交的Taco对象进行校验，会将错误捕捉到并封装到Errors对象中
	public String processDesign(@Valid Taco design, Errors errors,
			@ModelAttribute Order order) {
		// Errors.hasErrors() 判断是否有错误信息
		if(errors.hasErrors()) {
			return "design";
		}
		
		Taco saved = designRepo.save(design);
		order.addDesign(saved);
		// Save the taco design...
		// We'll do this in chapter 3
		// 此log对象由@Slf4j注解提供
		log.info("Processing design: " + design);
		// redirect：表示重定向
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
		return ingredients.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
}
