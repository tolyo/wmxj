/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Типы операций в системе WMT.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public enum OperationType {
	/**
	 * Обычная (или с протекцией, завершенная успешно).
	 */
	NORMAL(0), /**
	 * С протекцией, не завершена.
	 */
	PROTECTED_INCOMPLETE(4), /**
	 * С протекцией, вернулась.
	 */
	PROTECTED_RETURNED(12), /**
	 * Тип операции неопределен.
	 */
	UNKNOWN(-1);

	/**
	 * Типизирует операцию по ее WMT-коду.
	 * 
	 * @param code
	 *            код типа операции в системе WMT.
	 * @return тип операции
	 */
	public static OperationType getByValue(int code) {
		switch (code) {
		case 0:
			return NORMAL;
		case 4:
			return PROTECTED_INCOMPLETE;
		case 12:
			return PROTECTED_RETURNED;
		}
		return UNKNOWN;
	}

	/**
	 * Типизирует операцию по ее WMT-коду.
	 * 
	 * @param code
	 *            код типа операции в системе WMT.
	 * @return тип операции
	 */
	public static OperationType getByValue(String code) {
		return getByValue(Integer.parseInt(code));
	}

	/**
	 * Числовое значение типа операции в системе WMT.
	 */
	@SuppressWarnings("unused")
	private int value;

	private OperationType(int value) {
		this.value = value;
	}
}
