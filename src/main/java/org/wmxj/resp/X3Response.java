/**
 * 
 */
package org.wmxj.resp;

import org.wmxj.dict.Operation;

import java.util.List;

/**
 * Интерфейс X3: Получение истории операций по кошельку. Проверка выполнения
 * операции по переводу средств.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X3Response extends AbstractResponse {
	/**
	 * Количество найденных платежей, удовлетворяющих запросу (атрибут
	 * w3s.response/operations:cnt).
	 */
	private int count;
	/**
	 * Список платежей, полученных по запросу.
	 */
	private List<Operation> operationList;

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
	 * Список платежей, полученных по запросу.
	 * 
	 * @return Список платежей, полученных по запросу.
	 */
	public List<Operation> getOperationList() {
		return operationList;
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
	 * Список платежей, полученных по запросу.
	 * 
	 * @param operationList
	 *            Список платежей, полученных по запросу.
	 */
	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}
}
