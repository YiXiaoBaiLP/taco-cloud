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
		// 循环将数据保存到Taco_Ingredients中
		for(Ingredient ingredient : design.getIngredients()) {
			// 保存每种配料的信息（保存到Taco_Ingredients)
			saveIngredientToTaco(ingredient, tacoId);
		}
		return design;
	}

	/**
	 * 保存Taco信息
	 * @param taco
	 * @return
	 */
	private long saveTacoInfo(Taco taco) {
		taco.setCreateAt(new Date());
		
		/*
		 * Spring实战5 中此处代码有问题，修改方式如下
		// 提供KeyHolder所需要的信息
		PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
				"insert into Taco (name, createAt) values (?, ?)",
				// 每个查询的参数类型
				Types.VARCHAR, Types.TIMESTAMP)
		.newPreparedStatementCreator(
				Arrays.asList(
						taco.getName(),
						new Timestamp(taco.getCreateAt().getTime())));
		*/
		
		PreparedStatementCreatorFactory preFactory = 
				// 我们需要执行的sql
				new PreparedStatementCreatorFactory("insert inot Taco (name, createAt) values (?, ?) ",
			    // SQL中的参数类型
				Types.VARCHAR,
				Types.TIMESTAMP);
		// 设置自动返回主键，否则会导致getKey().longValue()空指针异常
		preFactory.setUpdatableResults(true);
		// 将查询参数所需要的值传递进来
		PreparedStatementCreator psc = preFactory.newPreparedStatementCreator(
				Arrays.asList(
						taco.getName(),
						new Timestamp(taco.getCreateAt().getTime())));
		// 提供生成的Taco ID 
		KeyHolder keyHolder = new GeneratedKeyHolder();
		// 保存配料信息
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
