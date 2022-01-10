package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

	private final String id;
	private final String name;
	private Type type;
	
	/**
	 * WRAP：包装
	 * PROTEIN：蛋白质
	 * VEGGIES：蔬菜
	 * CHEESE：奶酪
	 * SAUCE：酱汁
	 * @author yixiaobai
	 *
	 */
	private static enum Type{
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
