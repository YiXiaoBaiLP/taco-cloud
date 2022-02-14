package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository{
	
	private JdbcTemplate jdbc;

	public JdbcTacoRepository (JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Taco save(Taco design) {
		// 返回一个tacoId
		long tacoId = saveTacoInfo(design);
		design.setId(tacoId);
		for(Ingredient ingredient : design.getIngredients()) {
			// 保存每种配料的信息
			saveIngredientToTaco(ingredient, tacoId);
		}
		return design;
	}

	/**
	 * 
	 * @param taco
	 * @return
	 */
	private long saveTacoInfo(Taco taco) {
		taco.setCreateAt(new Date());
		// 提供KeyHolder所需要的信息
		PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
				"insert into Taco (name, createAt) values (?, ?)",
				// 每个查询的参数类型
				Types.VARCHAR, Types.TIMESTAMP)
		.newPreparedStatementCreator(
				Arrays.asList(
						taco.getName(),
						new Timestamp(taco.getCreateAt().getTime())));
		// 提供生成的Taco ID 
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		// 得到Taco ID
		return keyHolder.getKey().longValue();
	}
	
	/**
	 * 保存每种配料
	 * @param ingredient
	 * @param tacoId
	 */
	private void saveIngredientToTaco(Ingredient ingredient,long tacoId) {
		jdbc.update("insert into Taco_Ingredients (taco, ingredient) "
				+ "values (?, ?)",
				tacoId, 
				ingredient.getId());
	}
}
