/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Состояния счетов в системе WMT.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public enum InvoiceState {
	/**
	 * Оплачен окончательно или без протекции.
	 */
	PAID(2), /**
	 * Оплачен по протекции.
	 */
	PAID_PROTECTED(1), /**
	 * Отказан.
	 */
	REJECTED(3), /**
	 * Состояние счета не определено.
	 */
	UNKNOWN(-1), /**
	 * Не оплачен.
	 */
	UNPAID(0);

	/**
	 * Определяет состояние счета по его WMT-коду.
	 * 
	 * @param code
	 *            код состояния счета в системе WMT.
	 * @return состояние счета
	 */
	public static InvoiceState getByValue(int code) {
		switch (code) {
		case 0:
			return UNPAID;
		case 1:
			return PAID_PROTECTED;
		case 2:
			return PAID;
		case 3:
			return REJECTED;
		}
		return UNKNOWN;
	}

	/**
	 * Определяет состояние счета по его WMT-коду.
	 * 
	 * @param code
	 *            код состояния счета в системе WMT.
	 * @return состояние счета
	 */
	public static InvoiceState getByValue(String code) {
		return getByValue(Integer.parseInt(code));
	}

	/**
	 * Числовое значение состояния счета в системе WMT.
	 */
	@SuppressWarnings("unused")
	private int value;

	private InvoiceState(int value) {
		this.value = value;
	}
}
