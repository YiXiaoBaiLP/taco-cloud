package tacos.data;

import tacos.Order;

/**
 * 保存Order对象
 * @author yixiaobai
 *
 */
public interface OrderRepository {
	
	/**
	 * 保存Order对象
	 * @param order
	 * @return
	 */
	Order save(Order order);

}
