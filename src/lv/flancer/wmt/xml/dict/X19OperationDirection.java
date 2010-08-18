/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Направление операции в запросе X19.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public enum X19OperationDirection {
	INPUT(2), OUTPUT(1), UNKNOWN(0);
	/**
	 * Возвращает значение типа по его коду.
	 * 
	 * @param code
	 * @return значение типа
	 */
	public static X19OperationDirection getByValue(int code) {
		switch (code) {
		case 1:
			return OUTPUT;
		case 2:
			return INPUT;
		}
		return UNKNOWN;
	}

	@SuppressWarnings("unused")
	private int value;

	private X19OperationDirection(int value) {
		this.value = value;
	}
}
