/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Операция в терминах интерфейса X19.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X19Operation {
	/**
	 * Сумма перевода.
	 */
	private WmAmount amount;
	/**
	 * Направление операции в запросе X19.
	 */
	private X19OperationDirection direction;
	/**
	 * Тип WM-кошелька с (на) которого произведен перевод обязательный параметр
	 * (WMZ, WMR, WME, WMU, WMB, WMY, WMG).
	 */
	private String purseType;

	/**
	 * Тип операции в запросе X19 (input, output).
	 */
	private X19OperationType type;

	/**
	 * Сумма перевода.
	 * 
	 * @return Сумма перевода.
	 */
	public WmAmount getAmount() {
		return amount;
	}

	/**
	 * Направление операции в запросе X19.
	 * 
	 * @return Направление операции в запросе X19.
	 */
	public X19OperationDirection getDirection() {
		return direction;
	}

	/**
	 * Тип WM-кошелька с (на) которого произведен перевод обязательный параметр
	 * (WMZ, WMR, WME, WMU, WMB, WMY, WMG).
	 * 
	 * @return Тип WM-кошелька с (на) которого произведен перевод обязательный
	 *         параметр (WMZ, WMR, WME, WMU, WMB, WMY, WMG).
	 */
	public String getPurseType() {
		return purseType;
	}

	/**
	 * Тип операции в запросе X19.
	 * 
	 * @return Тип операции в запросе X19.
	 */
	public X19OperationType getType() {
		return type;
	}

	/**
	 * Сумма перевода.
	 * 
	 * @param amount
	 *            Сумма перевода.
	 */
	public void setAmount(double amount) {
		this.amount = new WmAmount(amount);
	}

	/**
	 * Сумма перевода.
	 * 
	 * @param amount
	 *            Сумма перевода.
	 */
	public void setAmount(WmAmount amount) {
		this.amount = amount;
	}

	/**
	 * Направление операции в запросе X19 (1, 2).
	 * 
	 * @param direction
	 *            Направление операции в запросе X19 (1, 2).
	 */
	public void setDirection(int direction) {
		this.direction = X19OperationDirection.getByValue(direction);
	}

	/**
	 * Направление операции в запросе X19.
	 * 
	 * @param direction
	 *            Направление операции в запросе X19.
	 */
	public void setDirection(String direction) {
		this.direction = X19OperationDirection.valueOf(direction.toUpperCase());
	}

	/**
	 * Направление операции в запросе X19.
	 * 
	 * @param direction
	 *            Направление операции в запросе X19.
	 */
	public void setDirection(X19OperationDirection direction) {
		this.direction = direction;
	}

	/**
	 * Тип WM-кошелька с (на) которого произведен перевод обязательный параметр
	 * (WMZ, WMR, WME, WMU, WMB, WMY, WMG).
	 * 
	 * @param purseType
	 *            Тип WM-кошелька с (на) которого произведен перевод
	 *            обязательный параметр (WMZ, WMR, WME, WMU, WMB, WMY, WMG).
	 */
	public void setPurseType(String purseType) {
		this.purseType = purseType;
	}

	/**
	 * Тип операции в запросе X19 (1, 2, ...).
	 * 
	 * @param type
	 *            Тип операции в запросе X19 (1, 2, ...).
	 */
	public void setType(int type) {
		this.type = X19OperationType.getByValue(type);
	}

	/**
	 * Тип операции в запросе X19 ('cash', 'sdp', ...).
	 * 
	 * @param type
	 *            Тип операции в запросе X19 ('cash', 'sdp', ...).
	 */
	public void setType(String type) {
		this.type = X19OperationType.valueOf(type.toUpperCase());
	}

	/**
	 * Тип операции в запросе X19.
	 * 
	 * @param type
	 *            Тип операции в запросе X19.
	 */
	public void setType(X19OperationType type) {
		this.type = type;
	}
}
