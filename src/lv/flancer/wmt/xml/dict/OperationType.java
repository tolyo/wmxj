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
	OUT_NORMAL(0), /**
	 * С протекцией, не завершена.
	 */
	OUT_PROTECTED_INCOMPLETE(4), /**
	 * С протекцией, вернулась.
	 */
	OUT_PROTECTED_RETURNED(12), /**
	 * Тип операции неопределен.
	 */
	UNKNOWN(-1);

	/**
	 * Типизирует операцию по ее WMT-коду.
	 * 
	 * @param value
	 * @return тип операции
	 */
	public static OperationType getByValue(int value) {
		switch (value) {
		case 0:
			return OUT_NORMAL;
		case 4:
			return OUT_PROTECTED_INCOMPLETE;
		case 12:
			return OUT_PROTECTED_RETURNED;
		}
		return UNKNOWN;
	}

	/**
	 * Типизирует операцию по ее WMT-коду.
	 * 
	 * @param value
	 * @return тип операции
	 */
	public static OperationType getByValue(String value) {
		return getByValue(Integer.parseInt(value));
	}

	/**
	 * Числовое значение типа операции в системе WMT.
	 */
	private int value;

	private OperationType(int value) {
		this.setValue(value);
	}

	/**
	 * Числовое значение типа операции в системе WMT.
	 * 
	 * @return Числовое значение типа операции в системе WMT.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Числовое значение типа операции в системе WMT.
	 * 
	 * @param value
	 *            Числовое значение типа операции в системе WMT.
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
