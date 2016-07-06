/**
 * 
 */
package org.wmxj.resp;

import org.wmxj.dict.WmPurse;

import java.util.List;

/**
 * Интерфейс X9: Получение информации о балансе на кошельках.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X9Response extends AbstractResponse {

	/**
	 * Количество найденных платежей, удовлетворяющих запросу (атрибут
	 * w3s.response/operations:cnt).
	 */
	private int count;
	/**
	 * Список кошельков и остатков на них, полученный по запросу.
	 */
	private List<WmPurse> purseList;

	/**
	 * Количество найденных платежей, удовлетворяющих запросу (атрибут
	 * w3s.response/operations/cnt).
	 * 
	 * @return Количество найденных платежей, удовлетворяющих запросу (атрибут
	 *         w3s.response/operations/cnt).
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Список кошельков и остатков на них, полученный по запросу.
	 * 
	 * @return Список кошельков и остатков на них, полученный по запросу.
	 */
	public List<WmPurse> getPurseList() {
		return purseList;
	}

	/**
	 * Количество найденных платежей, удовлетворяющих запросу (атрибут
	 * w3s.response/operations/cnt).
	 * 
	 * @param count
	 *            Количество найденных платежей, удовлетворяющих запросу
	 *            (атрибут w3s.response/operations/cnt).
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Количество найденных платежей, удовлетворяющих запросу (атрибут
	 * w3s.response/operations/cnt).
	 * 
	 * @param count
	 *            Количество найденных платежей, удовлетворяющих запросу
	 *            (атрибут w3s.response/operations/cnt).
	 */
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}

	/**
	 * Список кошельков и остатков на них, полученный по запросу.
	 * 
	 * @param purseList
	 *            Список кошельков и остатков на них, полученный по запросу.
	 */
	public void setPurseList(List<WmPurse> purseList) {
		this.purseList = purseList;
	}

}
