package tacos.data;

import tacos.Ingredient;

/**
 * 查询配料信息
 * @author yixiaobai
 *
 */
public interface IngredientRepository {

	/** 查询所有成分信息 */
	Iterable<Ingredient> findAll();
	/** 根据id查询一条成分信息 */
	Ingredient findOne(String id);
	/** 保存成分信息 */
	Ingredient save(Ingredient ingredient);
}
