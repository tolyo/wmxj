/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Тип операции в запросе X19.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public enum X19OperationType {
	BANK(3), CARD(4), CASH(1), EMONEY(5), SDP(2), SMS(6), UNKNOWN(0);
	/**
	 * Возвращает значение типа по его коду.
	 * 
	 * @param code
	 * @return значение типа
	 */
	public static X19OperationType getByValue(int code) {
		switch (code) {
		case 1:
			return CASH;
		case 2:
			return SDP;
		case 3:
			return BANK;
		case 4:
			return CARD;
		case 5:
			return EMONEY;
		case 6:
			return SMS;
		}
		return UNKNOWN;
	}

	@SuppressWarnings("unused")
	private int value;

	private X19OperationType(int value) {
		this.value = value;
	}
}
