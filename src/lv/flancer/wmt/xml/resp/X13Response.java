/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.OperationType;
import lv.flancer.wmt.xml.dict.WmDate;

/**
 * Интерфейс X13: Возврат незавершенного платежа с протекцией.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X13Response extends AbstractResponse {

	/**
	 * Дата и время последнего изменения состояния операции.
	 */
	private WmDate dateUpd;
	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 */
	private int id;
	/**
	 * Тип перевода (платежа).
	 */
	private OperationType operType;
	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 */
	private int ts;

	/**
	 * Дата и время последнего изменения состояния операции.
	 * 
	 * @return Дата и время последнего изменения состояния операции.
	 */
	public WmDate getDateUpd() {
		return dateUpd;
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @return Уникальный номер платежа в системе учета WebMoney.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Тип перевода (платежа).
	 * 
	 * @return Тип перевода (платежа).
	 */
	public OperationType getOperType() {
		return operType;
	}

	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 * 
	 * @return Служебный номер платежа в системе учета WebMoney.
	 */
	public int getTs() {
		return ts;
	}

	/**
	 * Дата и время последнего изменения состояния операции.
	 * 
	 * @param dateUpd
	 *            Дата и время последнего изменения состояния операции.
	 */
	public void setDateUpd(String dateUpd) {
		this.dateUpd = new WmDate(dateUpd);
	}

	/**
	 * Дата и время последнего изменения состояния операции.
	 * 
	 * @param dateUpd
	 *            Дата и время последнего изменения состояния операции.
	 */
	public void setDateUpd(WmDate dateUpd) {
		this.dateUpd = dateUpd;
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер платежа в системе учета WebMoney.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер платежа в системе учета WebMoney.
	 */
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	/**
	 * Тип перевода (платежа).
	 * 
	 * @param operType
	 *            Тип перевода (платежа).
	 */
	public void setOperType(OperationType operType) {
		this.operType = operType;
	}

	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 * 
	 * @param ts
	 *            Служебный номер платежа в системе учета WebMoney.
	 */
	public void setTs(int ts) {
		this.ts = ts;
	}

	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 * 
	 * @param ts
	 *            Служебный номер платежа в системе учета WebMoney.
	 */
	public void setTs(String ts) {
		this.ts = Integer.parseInt(ts);
	}

}
