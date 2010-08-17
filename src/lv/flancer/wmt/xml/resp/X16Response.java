/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.WmAmount;

/**
 * Интерфейс X16: Создание кошелька.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X16Response extends AbstractResponse {
	/**
	 * Сумма на кошельке.
	 */
	private WmAmount amount;
	/**
	 * Название кошелька.
	 */
	private String desc;
	/**
	 * Уникальный номер кошелька в системе учета WebMoney.
	 */
	private long id;

	/**
	 * Номер кошелька.
	 */
	private String purseName;

	/**
	 * Сумма на кошельке.
	 * 
	 * @return Сумма на кошельке.
	 */
	public WmAmount getAmount() {
		return amount;
	}

	/**
	 * Название кошелька.
	 * 
	 * @return Название кошелька.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Уникальный номер кошелька в системе учета WebMoney.
	 * 
	 * @return Уникальный номер кошелька в системе учета WebMoney.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Номер кошелька.
	 * 
	 * @return Номер кошелька.
	 */
	public String getPurseName() {
		return purseName;
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
	 * Название кошелька.
	 * 
	 * @param desc
	 *            Название кошелька.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Уникальный номер кошелька в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер кошелька в системе учета WebMoney.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Уникальный номер кошелька в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер кошелька в системе учета WebMoney.
	 */
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	/**
	 * Номер кошелька.
	 * 
	 * @param purseName
	 *            Номер кошелька.
	 */
	public void setPurseName(String purseName) {
		this.purseName = purseName;
	}
}
