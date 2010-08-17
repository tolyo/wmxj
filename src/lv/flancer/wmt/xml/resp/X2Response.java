/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.OperationType;
import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;
import lv.flancer.wmt.xml.dict.WmDate;

/**
 * Интерфейс X2: Перевод средств с одного кошелька на другой.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X2Response extends AbstractResponse {

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
	 * Тип перевода (платежа).
	 */
	private OperationType operType;
	/**
	 * Номер счета в системе магазина, выдавшего счет, по которому выполняется
	 * перевод.
	 */
	private long orderId;
	/**
	 * Срок протекции сделки в днях. Целое число от 0 до 255. Если 0 - операция
	 * без протекции сделки.
	 */
	private int period;
	/**
	 * Номер кошелька, на который выполняется перевод (получатель).
	 */
	private PurseNumber purseDest;
	/**
	 * Номер кошелька с которого выполняется перевод (отправитель).
	 */
	private PurseNumber purseSrc;
	/**
	 * Номер перевода в системе учета отправителя.
	 */
	private long tranId;
	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 */
	private long ts;
	/**
	 * Номер счета в системе WebMoney, по которому выполняется перевод.
	 */
	private long wmInvId;

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
	 * Тип перевода (платежа).
	 * 
	 * @return Тип перевода (платежа).
	 */
	public OperationType getOperType() {
		return operType;
	}

	/**
	 * Номер счета в системе магазина, выдавшего счет, по которому выполняется
	 * перевод.
	 * 
	 * @return Номер счета в системе магазина, выдавшего счет, по которому
	 *         выполняется перевод.
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Срок протекции сделки в днях. Целое число от 0 до 255. Если 0 - операция
	 * без протекции сделки.
	 * 
	 * @return Срок протекции сделки в днях.
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * Номер кошелька, на который выполняется перевод (получатель).
	 * 
	 * @return Номер кошелька, на который выполняется перевод (получатель).
	 */
	public PurseNumber getPurseDest() {
		return purseDest;
	}

	/**
	 * Номер кошелька с которого выполняется перевод (отправитель).
	 * 
	 * @return Номер кошелька с которого выполняется перевод (отправитель).
	 */
	public PurseNumber getPurseSrc() {
		return purseSrc;
	}

	/**
	 * Номер перевода в системе учета отправителя.
	 * 
	 * @return Номер перевода в системе учета отправителя.
	 */
	public long getTranId() {
		return tranId;
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
	 * Номер счета в системе WebMoney, по которому выполняется перевод.
	 * 
	 * @return Номер счета в системе WebMoney, по которому выполняется перевод.
	 */
	public long getWmInvId() {
		return wmInvId;
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
	 * Тип перевода (платежа).
	 * 
	 * @param operType
	 *            Тип перевода (платежа).
	 */
	public void setOperType(OperationType operType) {
		this.operType = operType;
	}

	/**
	 * Номер счета в системе магазина, выдавшего счет, по которому выполняется
	 * перевод.
	 * 
	 * @param orderId
	 *            Номер счета в системе магазина, выдавшего счет, по которому
	 *            выполняется перевод.
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Номер счета в системе магазина, выдавшего счет, по которому выполняется
	 * перевод.
	 * 
	 * @param orderId
	 *            Номер счета в системе магазина, выдавшего счет, по которому
	 *            выполняется перевод.
	 */
	public void setOrderId(String orderId) {
		this.orderId = Long.parseLong(orderId);
	}

	/**
	 * Срок протекции сделки в днях. Целое число от 0 до 255. Если 0 - операция
	 * без протекции сделки.
	 * 
	 * @param period
	 *            Срок протекции сделки в днях.
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * Срок протекции сделки в днях. Целое число от 0 до 255. Если 0 - операция
	 * без протекции сделки.
	 * 
	 * @param period
	 *            Срок протекции сделки в днях.
	 */
	public void setPeriod(String period) {
		this.period = Integer.parseInt(period);
	}

	/**
	 * Номер кошелька, на который выполняется перевод (получатель).
	 * 
	 * @param purseDest
	 *            Номер кошелька, на который выполняется перевод (получатель).
	 */
	public void setPurseDest(PurseNumber purseDest) {
		this.purseDest = purseDest;
	}

	/**
	 * Номер кошелька, на который выполняется перевод (получатель).
	 * 
	 * @param purseDest
	 *            Номер кошелька, на который выполняется перевод (получатель).
	 */
	public void setPurseDest(String purseDest) {
		this.purseDest = new PurseNumber(purseDest);
	}

	/**
	 * Номер кошелька с которого выполняется перевод (отправитель).
	 * 
	 * @param purseSrc
	 *            Номер кошелька с которого выполняется перевод (отправитель).
	 */
	public void setPurseSrc(PurseNumber purseSrc) {
		this.purseSrc = purseSrc;
	}

	/**
	 * Номер кошелька с которого выполняется перевод (отправитель).
	 * 
	 * @param purseSrc
	 *            Номер кошелька с которого выполняется перевод (отправитель).
	 */
	public void setPurseSrc(String purseSrc) {
		this.purseSrc = new PurseNumber(purseSrc);
	}

	/**
	 * Номер перевода в системе учета отправителя.
	 * 
	 * @param tranId
	 *            Номер перевода в системе учета отправителя.
	 */
	public void setTranId(long tranId) {
		this.tranId = tranId;
	}

	/**
	 * Номер перевода в системе учета отправителя.
	 * 
	 * @param tranId
	 *            Номер перевода в системе учета отправителя.
	 */
	public void setTranId(String tranId) {
		this.tranId = Long.parseLong(tranId);
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
	 * Номер счета в системе WebMoney, по которому выполняется перевод.
	 * 
	 * @param wmInvId
	 *            Номер счета в системе WebMoney, по которому выполняется
	 *            перевод.
	 */
	public void setWmInvId(long wmInvId) {
		this.wmInvId = wmInvId;
	}

	/**
	 * Номер счета в системе WebMoney, по которому выполняется перевод.
	 * 
	 * @param wmInvId
	 *            Номер счета в системе WebMoney, по которому выполняется
	 *            перевод.
	 */
	public void setWmInvId(String wmInvId) {
		this.wmInvId = Long.parseLong(wmInvId);
	}

}
