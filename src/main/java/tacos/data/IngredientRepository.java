package tacos.data;

import tacos.Ingredient;

/**
 * 查询配料信息
 * @author yixiaobai
 *
 */
public interface IngredientRepository {

	/** 查询所有配料信息 */
	Iterable<Ingredient> findAll();
	/** 根据id,查询单个Ingredient */
	Ingredient findOne(String id);
	/** 保存Ingredient对象 */
	Ingredient save(Ingredient ingredient);
}
