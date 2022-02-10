package tacos.data;

import tacos.Taco;

/**
 * 使用JdbcTemplate保存数据
 * @author yixiaobai
 *
 */
public interface TacoRepository {

	/** 保存 */
	Taco save(Taco design);
}
