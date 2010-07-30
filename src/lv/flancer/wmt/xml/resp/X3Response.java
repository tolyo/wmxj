/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import java.util.List;

import lv.flancer.wmt.xml.dict.Operation;

/**
 * X3. Получение истории операций по кошельку. Проверка выполнения операции по
 * переводу средств.
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
	 * Номер запроса, на который получен ответ.
	 */
	private long requestNum;
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
	 * Номер запроса, на который получен ответ.
	 * 
	 * @return Номер запроса, на который получен ответ.
	 */
	public long getRequestNum() {
		return requestNum;
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
	 * Список платежей, полученных по запросу.
	 * 
	 * @param operationList
	 *            Список платежей, полученных по запросу.
	 */
	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}

	/**
	 * Номер запроса, на который получен ответ.
	 * 
	 * @param requestNum
	 *            Номер запроса, на который получен ответ.
	 */
	public void setRequestNum(long requestNum) {
		this.requestNum = requestNum;
	}

	/**
	 * Номер запроса, на который получен ответ.
	 * 
	 * @param requestNum
	 *            Номер запроса, на который получен ответ.
	 */
	public void setRequestNum(String requestNum) {
		this.requestNum = Long.parseLong(requestNum);

	}
}
