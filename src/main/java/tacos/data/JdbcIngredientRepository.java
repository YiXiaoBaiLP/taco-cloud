package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient",
				this::mapRowToIngredient);
	}

	@Override
	// java8之后的方式
	public Ingredient findOne(String id) {
		
		return jdbc.queryForObject("select id, name, type from Ingredient where id = ?", 
				this::mapRowToIngredient, id);
	}
	
	// java8之前的方式
	public Ingredient findOne2(String id) {
		return jdbc.queryForObject("select id, name, type from Ingredient where id = ?", 
				// 一个匿名函数类
				new RowMapper<Ingredient>() {
					@Override
					public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Ingredient(
								rs.getString("id"),
								rs.getString("name"),
								Ingredient.Type.valueOf(rs.getString("type")));
					}
			}, id);
	}

	/**
	 * 保存数据
	 */
	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)", 
				ingredient.getId(), 
				ingredient.getName(),
				ingredient.getType());
		return ingredient;
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException{
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
	}	

}
