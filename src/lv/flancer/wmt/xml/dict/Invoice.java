/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Представление счета в системе WMT.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class Invoice {
	/**
	 * Адрес доставки товара.
	 */
	private String address;
	/**
	 * Cумма счета, выставленная для оплаты покупателю.
	 */
	private WmAmount amount;
	/**
	 * Кошелек плательщика, если счет оплачен.
	 */
	private PurseNumber customerPurse;
	/**
	 * WMID покупателя, которому был выписан счет.
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
	 * Уникальный номер счета в системе учета WebMoney (атрибут
	 * w3s.response/outinvoices/outinvoice:id).
	 */
	private long id;
	/**
	 * Номер счета в системе магазина, выписавшего счет.
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
	 * Номер кошелька, на который необходимо оплатить счет.
	 */
	private PurseNumber storePurse;
	/**
	 * WMID продавца, который выписал счет.
	 */
	private Wmid storeWmid;
	/**
	 * Служебный номер счета в системе учета WebMoney (атрибут
	 * w3s.response/outinvoices/outinvoice:ts).
	 */
	private long ts;
	/**
	 * Номер операции в системе WebMoney, если счет оплачен.
	 */
	private long wmTranId;

	/**
	 * Адрес доставки товара.
	 * 
	 * @return Адрес доставки товара.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Cумма счета, выставленная для оплаты покупателю.
	 * 
	 * @return Cумма счета, выставленная для оплаты покупателю.
	 */
	public WmAmount getAmount() {
		return amount;
	}

	/**
	 * Кошелек плательщика, если счет оплачен.
	 * 
	 * @return Кошелек плательщика, если счет оплачен.
	 */
	public PurseNumber getCustomerPurse() {
		return customerPurse;
	}

	/**
	 * WMID покупателя, которому был выписан счет.
	 * 
	 * @return WMID покупателя, которому был выписан счет.
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
	 * Уникальный номер счета в системе учета WebMoney (атрибут
	 * w3s.response/outinvoices/outinvoice:id).
	 * 
	 * @return Уникальный номер счета в системе учета WebMoney (атрибут
	 *         w3s.response/outinvoices/outinvoice:id).
	 */
	public long getId() {
		return id;
	}

	/**
	 * Номер счета в системе магазина, выписавшего счет.
	 * 
	 * @return Номер счета в системе магазина, выписавшего счет.
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
	 * Номер кошелька, на который необходимо оплатить счет.
	 * 
	 * @return Номер кошелька, на который необходимо оплатить счет.
	 */
	public PurseNumber getStorePurse() {
		return storePurse;
	}

	/**
	 * WMID продавца, который выписал счет.
	 * 
	 * @return WMID продавца, который выписал счет.
	 */
	public Wmid getStoreWmid() {
		return storeWmid;
	}

	/**
	 * Служебный номер счета в системе учета WebMoney (атрибут
	 * w3s.response/outinvoices/outinvoice:ts).
	 * 
	 * @return Служебный номер счета в системе учета WebMoney (атрибут
	 *         w3s.response/outinvoices/outinvoice:ts).
	 */
	public long getTs() {
		return ts;
	}

	/**
	 * Номер операции в системе WebMoney, если счет оплачен.
	 * 
	 * @return Номер операции в системе WebMoney, если счет оплачен.
	 */
	public long getWmTranId() {
		return wmTranId;
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
	 * Cумма счета, выставленная для оплаты покупателю.
	 * 
	 * @param amount
	 *            Cумма счета, выставленная для оплаты покупателю.
	 */
	public void setAmount(String amount) {
		this.amount = new WmAmount(amount);
	}

	/**
	 * Cумма счета, выставленная для оплаты покупателю.
	 * 
	 * @param amount
	 *            Cумма счета, выставленная для оплаты покупателю.
	 */
	public void setAmount(WmAmount amount) {
		this.amount = amount;
	}

	/**
	 * Кошелек плательщика, если счет оплачен.
	 * 
	 * @param customerPurse
	 *            Кошелек плательщика, если счет оплачен.
	 */
	public void setCustomerPurse(PurseNumber customerPurse) {
		this.customerPurse = customerPurse;
	}

	/**
	 * Кошелек плательщика, если счет оплачен.
	 * 
	 * @param customerPurse
	 *            Кошелек плательщика, если счет оплачен.
	 */
	public void setCustomerPurse(String customerPurse) {
		this.customerPurse = new PurseNumber(customerPurse);
	}

	/**
	 * WMID покупателя, которому был выписан счет.
	 * 
	 * @param customerWmid
	 *            WMID покупателя, которому был выписан счет.
	 */
	public void setCustomerWmid(String customerWmid) {
		this.customerWmid = new Wmid(customerWmid);
	}

	/**
	 * WMID покупателя, которому был выписан счет.
	 * 
	 * @param customerWmid
	 *            WMID покупателя, которому был выписан счет.
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
	 * Уникальный номер счета в системе учета WebMoney (атрибут
	 * w3s.response/outinvoices/outinvoice:id).
	 * 
	 * @param id
	 *            Уникальный номер счета в системе учета WebMoney (атрибут
	 *            w3s.response/outinvoices/outinvoice:id).
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Уникальный номер счета в системе учета WebMoney (атрибут
	 * w3s.response/outinvoices/outinvoice:id).
	 * 
	 * @param id
	 *            Уникальный номер счета в системе учета WebMoney (атрибут
	 *            w3s.response/outinvoices/outinvoice:id).
	 */
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	/**
	 * Номер счета в системе магазина, выписавшего счет.
	 * 
	 * @param orderId
	 *            Номер счета в системе магазина, выписавшего счет.
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Номер счета в системе магазина, выписавшего счет.
	 * 
	 * @param orderId
	 *            Номер счета в системе магазина, выписавшего счет.
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
	 * Номер кошелька, на который необходимо оплатить счет.
	 * 
	 * @param storePurse
	 *            Номер кошелька, на который необходимо оплатить счет.
	 */
	public void setStorePurse(PurseNumber storePurse) {
		this.storePurse = storePurse;
	}

	/**
	 * Номер кошелька, на который необходимо оплатить счет.
	 * 
	 * @param storePurse
	 *            Номер кошелька, на который необходимо оплатить счет.
	 */
	public void setStorePurse(String storePurse) {
		this.storePurse = new PurseNumber(storePurse);
	}

	/**
	 * WMID продавца, который выписал счет.
	 * 
	 * @param storeWmid
	 *            WMID продавца, который выписал счет.
	 */
	public void setStoreWmid(String storeWmid) {
		this.storeWmid = new Wmid(storeWmid);
	}

	/**
	 * WMID продавца, который выписал счет.
	 * 
	 * @param storeWmid
	 *            WMID продавца, который выписал счет.
	 */
	public void setStoreWmid(Wmid storeWmid) {
		this.storeWmid = storeWmid;
	}

	/**
	 * Служебный номер счета в системе учета WebMoney (атрибут
	 * w3s.response/outinvoices/outinvoice:ts).
	 * 
	 * @param ts
	 *            Служебный номер счета в системе учета WebMoney (атрибут
	 *            w3s.response/outinvoices/outinvoice:ts).
	 */
	public void setTs(long ts) {
		this.ts = ts;
	}

	/**
	 * Служебный номер счета в системе учета WebMoney (атрибут
	 * w3s.response/outinvoices/outinvoice:ts).
	 * 
	 * @param ts
	 *            Служебный номер счета в системе учета WebMoney (атрибут
	 *            w3s.response/outinvoices/outinvoice:ts).
	 */
	public void setTs(String ts) {
		this.ts = Long.parseLong(ts);
	}

	/**
	 * Номер операции в системе WebMoney, если счет оплачен.
	 * 
	 * @param wmTranid
	 *            Номер операции в системе WebMoney, если счет оплачен.
	 */
	public void setWmTranid(String wmTranid) {
		this.wmTranId = Long.parseLong(wmTranid);
	}

	/**
	 * Номер операции в системе WebMoney, если счет оплачен.
	 * 
	 * @param wmTranid
	 *            Номер операции в системе WebMoney, если счет оплачен.
	 */
	public void setWmTranId(long wmTranid) {
		this.wmTranId = wmTranid;
	}

}
