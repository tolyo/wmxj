/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * WebMoney-кошелек.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class WmPurse {
	/**
	 * Сумма на кошельке.
	 */
	private WmAmount amount;
	/**
	 * Описание кошелька.
	 */
	private String desc;
	/**
	 * Уникальный внутренний номер кошелька в WebMoney.
	 */
	private long id;
	/**
	 * ID последней входящей транзакции (???).
	 */
	private long lastInTr;

	/**
	 * ID последней исходящей транзакции (???).
	 */
	private long lastOutTr;

	/**
	 * Номер кошелька.
	 */
	private PurseNumber number;

	/**
	 * Недокументировано.
	 */
	private int outsideOpen;

	/**
	 * Сумма на кошельке.
	 * 
	 * @return Сумма на кошельке.
	 */
	public WmAmount getAmount() {
		return amount;
	}

	/**
	 * Описание кошелька.
	 * 
	 * @return Описание кошелька.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Уникальный внутренний номер кошелька в WebMoney.
	 * 
	 * @return Уникальный внутренний номер кошелька в WebMoney.
	 */
	public long getId() {
		return id;
	}

	/**
	 * ID последней входящей транзакции (???).
	 * 
	 * @return ID последней входящей транзакции (???).
	 */
	public long getLastInTr() {
		return lastInTr;
	}

	/**
	 * ID последней исходящей транзакции (???).
	 * 
	 * @return ID последней исходящей транзакции (???).
	 */
	public long getLastOutTr() {
		return lastOutTr;
	}

	/**
	 * Номер кошелька.
	 * 
	 * @return Номер кошелька.
	 */
	public PurseNumber getNumber() {
		return number;
	}

	/**
	 * Недокументировано.
	 * 
	 * @return Недокументировано.
	 */
	public int getOutsideOpen() {
		return outsideOpen;
	}

	/**
	 * Сумма на кошельке.
	 * 
	 * @param amount
	 *            Сумма на кошельке.
	 */
	public void setAmount(double amount) {
		this.amount = new WmAmount(amount);
	}

	/**
	 * Сумма на кошельке.
	 * 
	 * @param amount
	 *            Сумма на кошельке.
	 */
	public void setAmount(String amount) {
		this.amount = new WmAmount(amount);
	}

	/**
	 * Сумма на кошельке.
	 * 
	 * @param amount
	 *            Сумма на кошельке.
	 */
	public void setAmount(WmAmount amount) {
		this.amount = amount;
	}

	/**
	 * Описание кошелька.
	 * 
	 * @param desc
	 *            Описание кошелька.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Уникальный внутренний номер кошелька в WebMoney.
	 * 
	 * @param id
	 *            Уникальный внутренний номер кошелька в WebMoney.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Уникальный внутренний номер кошелька в WebMoney.
	 * 
	 * @param id
	 *            Уникальный внутренний номер кошелька в WebMoney.
	 */
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	/**
	 * ID последней входящей транзакции (???).
	 * 
	 * @param lastInTr
	 *            ID последней входящей транзакции (???).
	 */
	public void setLastInTr(long lastInTr) {
		this.lastInTr = lastInTr;
	}

	/**
	 * ID последней входящей транзакции (???).
	 * 
	 * @param lastInTr
	 *            ID последней входящей транзакции (???).
	 */
	public void setLastInTr(String lastInTr) {
		this.lastInTr = Long.parseLong(lastInTr);
	}

	/**
	 * ID последней исходящей транзакции (???).
	 * 
	 * @param lastOutTr
	 *            ID последней исходящей транзакции (???).
	 */
	public void setLastOutTr(long lastOutTr) {
		this.lastOutTr = lastOutTr;
	}

	/**
	 * ID последней исходящей транзакции (???).
	 * 
	 * @param lastOutTr
	 *            ID последней исходящей транзакции (???).
	 */
	public void setLastOutTr(String lastOutTr) {
		this.lastOutTr = Long.parseLong(lastOutTr);
	}

	/**
	 * Номер кошелька.
	 * 
	 * @param number
	 *            Номер кошелька.
	 */
	public void setNumber(PurseNumber number) {
		this.number = number;
	}

	/**
	 * Номер кошелька.
	 * 
	 * @param number
	 *            Номер кошелька.
	 */
	public void setNumber(String number) {
		this.number = new PurseNumber(number);
	}

	/**
	 * Недокументировано.
	 * 
	 * @param outsideOpen
	 *            Недокументировано.
	 */
	public void setOutsideOpen(int outsideOpen) {
		this.outsideOpen = outsideOpen;
	}

	/**
	 * Недокументировано.
	 * 
	 * @param outsideOpen
	 *            Недокументировано.
	 */
	public void setOutsideOpen(String outsideOpen) {
		this.outsideOpen = Integer.parseInt(outsideOpen);
	}

}
