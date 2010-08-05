/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.OperationType;
import lv.flancer.wmt.xml.dict.WmDate;

/**
 * Интерфейс X5: Завершение операции с протекцией сделки. Ввод кода протекции.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X5Response extends AbstractResponse {
	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 */
	private long id;
	/**
	 * Тип платежа.
	 */
	private OperationType operType;
	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 */
	private long ts;
	/**
	 * Дата и время последнего изменения состояния операции.
	 */
	private WmDate dateUpd;

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @return Уникальный номер платежа в системе учета WebMoney.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Тип платежа.
	 * 
	 * @return Тип платежа.
	 */
	public OperationType getOperType() {
		return operType;
	}

	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 * 
	 * @return Служебный номер платежа в системе учета WebMoney.
	 */
	public long getTs() {
		return ts;
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер платежа в системе учета WebMoney.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер платежа в системе учета WebMoney.
	 */
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	/**
	 * Тип платежа.
	 * 
	 * @param operType
	 *            Тип платежа.
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
	public void setTs(long ts) {
		this.ts = ts;
	}

	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 * 
	 * @param ts
	 *            Служебный номер платежа в системе учета WebMoney.
	 */
	public void setTs(String ts) {
		this.ts = Long.parseLong(ts);
	}

	/**
	 * Дата и время последнего изменения состояния счета.
	 * 
	 * @return Дата и время последнего изменения состояния счета.
	 */
	public WmDate getDateUpd() {
		return dateUpd;
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

}
