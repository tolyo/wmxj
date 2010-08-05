/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.InvoiceState;
import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;
import lv.flancer.wmt.xml.dict.WmDate;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X1: Выписывание счета от одного участника (магазина, ресурса)
 * другому участнику (покупателю).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X1Response extends AbstractResponse {

	/**
	 * Адрес доставки товара.
	 */
	private String address;
	/**
	 * Сумма счета, выставленная для оплаты покупателю.
	 */
	private WmAmount amount;
	/**
	 * Wmid покупателя.
	 */
	private Wmid customerWmid;
	/**
	 * Дата и время создания счета.
	 */
	private WmDate dateCrt;
	/**
	 * Дата и время последнего изменения состояния счета.
	 */
	private WmDate dateUpd;
	/**
	 * Описание товара или услуги, на который выписывается счет.
	 */
	private String desc;
	/**
	 * Максимально допустимый срок оплаты счета в днях.
	 */
	private int expiration;
	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 */
	private int id;
	/**
	 * Номер счета в системе учета магазина.
	 */
	private long orderId;
	/**
	 * Максимально допустимый срок протекции сделки в днях при оплате счета.
	 */
	private int period;

	/**
	 * Состояние счета.
	 */
	private InvoiceState state;
	/**
	 * Номер кошелька, но который необходимо оплатить счет.
	 */
	private PurseNumber storePurse;
	/**
	 * Служебный номер платежа в системе учета WebMoney.
	 */
	private int ts;

	/**
	 * Адрес доставки товара.
	 * 
	 * @return Адрес доставки товара.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Сумма счета, выставленная для оплаты покупателю.
	 * 
	 * @return Сумма счета, выставленная для оплаты покупателю.
	 */
	public WmAmount getAmount() {
		return amount;
	}

	/**
	 * Wmid покупателя.
	 * 
	 * @return Wmid покупателя.
	 */
	public Wmid getCustomerWmid() {
		return customerWmid;
	}

	/**
	 * Дата и время создания счета.
	 * 
	 * @return Дата и время создания счета.
	 */
	public WmDate getDateCrt() {
		return dateCrt;
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
	 * Описание товара или услуги, на который выписывается счет.
	 * 
	 * @return Описание товара или услуги, на который выписывается счет.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Максимально допустимый срок оплаты счета в днях.
	 * 
	 * @return Максимально допустимый срок оплаты счета в днях.
	 */
	public int getExpiration() {
		return expiration;
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
	 * Номер счета в системе учета магазина.
	 * 
	 * @return Номер счета в системе учета магазина.
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Максимально допустимый срок протекции сделки в днях при оплате счета.
	 * 
	 * @return Максимально допустимый срок протекции сделки в днях при оплате
	 *         счета.
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * Состояние счета.
	 * 
	 * @return Состояние счета.
	 */
	public InvoiceState getState() {
		return state;
	}

	/**
	 * Номер кошелька, но который необходимо оплатить счет.
	 * 
	 * @return Номер кошелька, но который необходимо оплатить счет.
	 */
	public PurseNumber getStorePurse() {
		return storePurse;
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
	 * Адрес доставки товара.
	 * 
	 * @param address
	 *            Адрес доставки товара.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Сумма счета, выставленная для оплаты покупателю.
	 * 
	 * @param amount
	 *            Сумма счета, выставленная для оплаты покупателю.
	 */
	public void setAmount(double amount) {
		this.amount = new WmAmount(amount);
	}

	/**
	 * Сумма счета, выставленная для оплаты покупателю.
	 * 
	 * @param amount
	 *            Сумма счета, выставленная для оплаты покупателю.
	 */
	public void setAmount(String amount) {
		this.amount = new WmAmount(amount);
	}

	/**
	 * Wmid покупателя.
	 * 
	 * @param customerWmid
	 *            Wmid покупателя.
	 */
	public void setCustomerWmid(String customerWmid) {
		this.customerWmid = new Wmid(customerWmid);
	}

	/**
	 * Wmid покупателя.
	 * 
	 * @param customerWmid
	 *            Wmid покупателя.
	 */
	public void setCustomerWmid(Wmid customerWmid) {
		this.customerWmid = customerWmid;
	}

	/**
	 * Дата и время создания счета.
	 * 
	 * @param dateCrt
	 *            Дата и время создания счета.
	 */
	public void setDateCrt(String dateCrt) {
		this.dateCrt = new WmDate(dateCrt);
	}

	/**
	 * Дата и время создания счета.
	 * 
	 * @param dateCrt
	 *            Дата и время создания счета.
	 */
	public void setDateCrt(WmDate dateCrt) {
		this.dateCrt = dateCrt;
	}

	/**
	 * Дата и время последнего изменения состояния счета.
	 * 
	 * @param dateUpd
	 *            Дата и время последнего изменения состояния счета.
	 */
	public void setDateUpd(String dateUpd) {
		this.dateUpd = new WmDate(dateUpd);
	}

	/**
	 * Дата и время последнего изменения состояния счета.
	 * 
	 * @param dateUpd
	 *            Дата и время последнего изменения состояния счета.
	 */
	public void setDateUpd(WmDate dateUpd) {
		this.dateUpd = dateUpd;
	}

	/**
	 * Описание товара или услуги, на который выписывается счет.
	 * 
	 * @param desc
	 *            Описание товара или услуги, на который выписывается счет.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Максимально допустимый срок оплаты счета в днях.
	 * 
	 * @param expiration
	 *            Максимально допустимый срок оплаты счета в днях.
	 */
	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	/**
	 * Максимально допустимый срок оплаты счета в днях.
	 * 
	 * @param expiration
	 *            Максимально допустимый срок оплаты счета в днях.
	 */
	public void setExpiration(String expiration) {
		this.expiration = Integer.parseInt(expiration);
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
	 * Уникальный номер платежа в системе учета WebMoney .
	 * 
	 * @param id
	 *            Уникальный номер платежа в системе учета WebMoney.
	 */
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	/**
	 * Номер счета в системе учета магазина.
	 * 
	 * @param orderId
	 *            Номер счета в системе учета магазина.
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Номер счета в системе учета магазина.
	 * 
	 * @param orderId
	 *            Номер счета в системе учета магазина.
	 */
	public void setOrderId(String orderId) {
		this.orderId = Long.parseLong(orderId);
	}

	/**
	 * Максимально допустимый срок протекции сделки в днях при оплате счета.
	 * 
	 * @param period
	 *            Максимально допустимый срок протекции сделки в днях при оплате
	 *            счета.
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * Максимально допустимый срок протекции сделки в днях при оплате счета.
	 * 
	 * @param period
	 *            Максимально допустимый срок протекции сделки в днях при оплате
	 *            счета.
	 */
	public void setPeriod(String period) {
		this.period = Integer.parseInt(period);
	}

	/**
	 * Состояние счета.
	 * 
	 * @param state
	 *            Состояние счета.
	 */
	public void setState(InvoiceState state) {
		this.state = state;
	}

	/**
	 * Состояние счета.
	 * 
	 * @param state
	 *            Состояние счета.
	 */
	public void setState(String state) {
		this.state = InvoiceState.getByValue(state);
	}

	/**
	 * Номер кошелька, но который необходимо оплатить счет.
	 * 
	 * @param storePurse
	 *            Номер кошелька, но который необходимо оплатить счет.
	 */
	public void setStorePurse(PurseNumber storePurse) {
		this.storePurse = storePurse;
	}

	/**
	 * Номер кошелька, но который необходимо оплатить счет.
	 * 
	 * @param storePurse
	 *            Номер кошелька, но который необходимо оплатить счет.
	 */
	public void setStorePurse(String storePurse) {
		this.storePurse = new PurseNumber(storePurse);
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
