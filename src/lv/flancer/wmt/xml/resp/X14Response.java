/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;
import lv.flancer.wmt.xml.dict.WmDate;

/**
 * Интерфейс X14: Бескомиссионный возврат средств отправителю (покупателю).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X14Response extends AbstractResponse {
	/**
	 * Сумма платежа.
	 */
	private WmAmount amount;
	/**
	 * Комиссия за выполненный платеж.
	 */
	private WmAmount comiss;
	/**
	 * Дата и время выполнения операции.
	 */
	private WmDate dateCrt;
	/**
	 * Дата и время последнего изменения состояния операции.
	 */
	private WmDate dateUpd;
	/**
	 * Описание оплачиваемого товара или услуги.
	 */
	private String desc;
	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 */
	private long id;
	/**
	 * Номер возвращаемой транзакции.
	 */
	private long inWmTranId;
	/**
	 * Номер кошелька, на который произведен возврат и с которого была
	 * произведена транзакция inwmtranid.
	 */
	private PurseNumber purseDest;
	/**
	 * Номер кошелька, с которого произведен возврат и на который поступила
	 * транзакция inwmtranid.
	 */
	private PurseNumber purseSrc;
	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 */
	private long ts;

	/**
	 * Сумма платежа.
	 * 
	 * @return Сумма платежа.
	 */
	public WmAmount getAmount() {
		return amount;
	}

	/**
	 * Комиссия за выполненный платеж.
	 * 
	 * @return Комиссия за выполненный платеж.
	 */
	public WmAmount getComiss() {
		return comiss;
	}

	/**
	 * Дата и время выполнения операции.
	 * 
	 * @return Дата и время выполнения операции.
	 */
	public WmDate getDateCrt() {
		return dateCrt;
	}

	/**
	 * Дата и время последнего изменения состояния операции.
	 * 
	 * @return Дата и время последнего изменения состояния операции.
	 */
	public WmDate getDateUpd() {
		return dateUpd;
	}

	/**
	 * Описание оплачиваемого товара или услуги.
	 * 
	 * @return Описание оплачиваемого товара или услуги.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @return Уникальный номер платежа в системе учета WebMoney.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Номер возвращаемой транзакции.
	 * 
	 * @return Номер возвращаемой транзакции.
	 */
	public long getInWmTranId() {
		return inWmTranId;
	}

	/**
	 * Номер кошелька, на который произведен возврат и с которого была
	 * произведена транзакция inwmtranid.
	 * 
	 * @return Номер кошелька, на который произведен возврат и с которого была
	 *         произведена транзакция inwmtranid.
	 */
	public PurseNumber getPurseDest() {
		return purseDest;
	}

	/**
	 * Номер кошелька, с которого произведен возврат и на который поступила
	 * транзакция inwmtranid.
	 * 
	 * @return Номер кошелька, с которого произведен возврат и на который
	 *         поступила транзакция inwmtranid.
	 */
	public PurseNumber getPurseSrc() {
		return purseSrc;
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
	 * Сумма платежа.
	 * 
	 * @param amount
	 *            Сумма платежа.
	 */
	public void setAmount(String amount) {
		this.amount = new WmAmount(amount);
	}

	/**
	 * Сумма платежа.
	 * 
	 * @param amount
	 *            Сумма платежа.
	 */
	public void setAmount(WmAmount amount) {
		this.amount = amount;
	}

	/**
	 * Комиссия за выполненный платеж.
	 * 
	 * @param comiss
	 *            Комиссия за выполненный платеж.
	 */
	public void setComiss(String comiss) {
		this.comiss = new WmAmount(comiss);
	}

	/**
	 * Комиссия за выполненный платеж.
	 * 
	 * @param comiss
	 *            Комиссия за выполненный платеж.
	 */
	public void setComiss(WmAmount comiss) {
		this.comiss = comiss;
	}

	/**
	 * Дата и время выполнения операции.
	 * 
	 * @param dateCrt
	 *            Дата и время выполнения операции.
	 */
	public void setDateCrt(String dateCrt) {
		this.dateCrt = new WmDate(dateCrt);
	}

	/**
	 * Дата и время выполнения операции.
	 * 
	 * @param dateCrt
	 *            Дата и время выполнения операции.
	 */
	public void setDateCrt(WmDate dateCrt) {
		this.dateCrt = dateCrt;
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
	 * Описание оплачиваемого товара или услуги.
	 * 
	 * @param desc
	 *            Описание оплачиваемого товара или услуги.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
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
	 * Номер возвращаемой транзакции.
	 * 
	 * @param inWmTranId
	 *            Номер возвращаемой транзакции.
	 */
	public void setInWmTranId(long inWmTranId) {
		this.inWmTranId = inWmTranId;
	}

	/**
	 * Номер возвращаемой транзакции.
	 * 
	 * @param inWmTranId
	 *            Номер возвращаемой транзакции.
	 */
	public void setInWmTranId(String inWmTranId) {
		this.inWmTranId = Long.parseLong(inWmTranId);
	}

	/**
	 * Номер кошелька, на который произведен возврат и с которого была
	 * произведена транзакция inwmtranid.
	 * 
	 * @param purseDest
	 *            Номер кошелька, на который произведен возврат и с которого
	 *            была произведена транзакция inwmtranid.
	 */
	public void setPurseDest(PurseNumber purseDest) {
		this.purseDest = purseDest;
	}

	/**
	 * Номер кошелька, на который произведен возврат и с которого была
	 * произведена транзакция inwmtranid.
	 * 
	 * @param purseDest
	 *            Номер кошелька, на который произведен возврат и с которого
	 *            была произведена транзакция inwmtranid.
	 */
	public void setPurseDest(String purseDest) {
		this.purseDest = new PurseNumber(purseDest);
	}

	/**
	 * Номер кошелька, с которого произведен возврат и на который поступила
	 * транзакция inwmtranid.
	 * 
	 * @param purseSrc
	 *            Номер кошелька, с которого произведен возврат и на который
	 *            поступила транзакция inwmtranid.
	 */
	public void setPurseSrc(PurseNumber purseSrc) {
		this.purseSrc = purseSrc;
	}

	/**
	 * Номер кошелька, с которого произведен возврат и на который поступила
	 * транзакция inwmtranid.
	 * 
	 * @param purseSrc
	 *            Номер кошелька, с которого произведен возврат и на который
	 *            поступила транзакция inwmtranid.
	 */
	public void setPurseSrc(String purseSrc) {
		this.purseSrc = new PurseNumber(purseSrc);
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

}
