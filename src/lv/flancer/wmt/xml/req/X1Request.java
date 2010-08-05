/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X1: Выписывание счета от одного участника (магазина, ресурса)
 * другому участнику (покупателю).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X1Request extends AbstractRequest {
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
	 * Описание товара или услуги, на который выписывается счет.
	 */
	private String desc;

	/**
	 * Максимально допустимый срок оплаты счета в днях.
	 */
	private int expiration;

	/**
	 * Номер счета в системе учета магазина.
	 */
	private long orderId;
	/**
	 * Максимально допустимый срок протекции сделки в днях при оплате счета.
	 */
	private int period;

	/**
	 * Номер кошелька, но который необходимо оплатить счет.
	 */
	private PurseNumber storePurse;

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
	 * Номер кошелька, но который необходимо оплатить счет.
	 * 
	 * @return Номер кошелька, но который необходимо оплатить счет.
	 */
	public PurseNumber getStorePurse() {
		return storePurse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.orderId;
		result += this.customerWmid;
		result += this.storePurse;
		result += this.amount.getWmFormated();
		result += this.desc;
		result += this.address;
		result += this.period;
		result += this.expiration;
		result += this.requestNum;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getXmlRequest()
	 */
	@Override
	public String getXmlRequest() {
		String result = "<?xml version=\"1.0\"  encoding=\"windows-1251\"?>";
		result += "<w3s.request>";
		result += "<reqn>" + this.requestNum + "</reqn>";
		if (this.signerWmid != null) {
			result += "<wmid>" + this.signerWmid + "</wmid>";
		} else {
			result += "<wmid />";
		}
		if (this.sign != null) {
			result += "<sign>" + this.sign + "</sign>";
		} else {
			result += "<sign />";
		}
		result += "<invoice>";
		result += "<orderid>" + this.getOrderId() + "</orderid>";
		result += "<customerwmid>" + this.getCustomerWmid() + "</customerwmid>";
		result += "<storepurse>" + this.getStorePurse() + "</storepurse>";
		result += "<amount>" + this.getAmount().getWmFormated() + "</amount>";
		result += "<desc>" + this.getDesc() + "</desc>";
		result += "<address>" + this.getAddress() + "</address>";
		result += "<period>" + this.getPeriod() + "</period>";
		result += "<expiration>" + this.getExpiration() + "</expiration>";
		result += "</invoice>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Адрес доставки товара.
	 * 
	 * @param address
	 *            Адрес доставки товара.
	 */
	public void setAddress(String address) {
		this.address = this.prepareString(address, 255, true);
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
	 * Сумма счета, выставленная для оплаты покупателю.
	 * 
	 * @param amount
	 *            Сумма счета, выставленная для оплаты покупателю.
	 */
	public void setAmount(WmAmount amount) {
		this.amount = amount;
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
	 * Описание товара или услуги, на который выписывается счет.
	 * 
	 * @param desc
	 *            Описание товара или услуги, на который выписывается счет.
	 */
	public void setDesc(String desc) {
		this.desc = this.prepareString(desc, 255, true);
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

}
