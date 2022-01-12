package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
 * Lombok所提供的注解
 * 		@Data：All together now:
 * 	A shortcut for
 *  	@ToString
 *  	@EnqualsAndHashCode
 *  	@Getter on all fields
 *  	@Setter on all non-final fields
 *  	@RequiredArgsConstructor
 * 
 */
@Data
@RequiredArgsConstructor
public class Ingredient {

	private final String id;
	private final String name;
	private final Type type;
	
	/**
	 * WRAP：包装
	 * PROTEIN：蛋白质
	 * VEGGIES：蔬菜
	 * CHEESE：奶酪
	 * SAUCE：酱汁
	 * @author yixiaobai
	 */
	public static enum Type{
		/** 包装 */
		WRAP,
		/** 蛋白质 */
		PROTEIN,
		/** 蔬菜 */
		VEGGIES,
		/** 奶酪 */
		CHEESE,
		/** 酱汁 */
		SAUCE
	}
}
