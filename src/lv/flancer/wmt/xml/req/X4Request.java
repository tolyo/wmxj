/**
 * 
 */
package lv.flancer.wmt.xml.req;

import java.util.Date;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmDate;

/**
 * Интерфейс X4: Получение истории выписанных счетов по кошельку. Проверка
 * оплаты счета.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X4Request extends AbstractRequest {
	/**
	 * Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	private WmDate dateFinish;
	/**
	 * Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	private WmDate dateStart;
	/**
	 * Номер счета в системе учета магазина.
	 */
	private long orderId;
	/**
	 * Номер кошелька для оплаты на который выписывался счет.
	 */
	private PurseNumber purse;
	/**
	 * Номер счета в системе WebMoney.
	 */
	private long wmInvId;

	/**
	 * Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @return Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public WmDate getDateFinish() {
		return dateFinish;
	}

	/**
	 * Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @return Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public WmDate getDateStart() {
		return dateStart;
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
	 * Номер кошелька для оплаты на который выписывался счет.
	 * 
	 * @return Номер кошелька для оплаты на который выписывался счет.
	 */
	public PurseNumber getPurse() {
		return purse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.purse;
		result += this.requestNum;
		return result;
	}

	/**
	 * Номер счета в системе WebMoney.
	 * 
	 * @return Номер счета в системе WebMoney.
	 */
	public long getWmInvId() {
		return wmInvId;
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
		result += "<getoutinvoices>";
		result += "<purse>" + this.purse + "</purse>";
		result += "<wminvid>" + this.wmInvId + "</wminvid>";
		result += "<orderid>" + this.orderId + "</orderid>";
		result += "<datestart>" + this.dateStart.getWmtDate() + "</datestart>";
		result += "<datefinish>" + this.dateFinish.getWmtDate()
				+ "</datefinish>";
		result += "</getoutinvoices>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @param dateFinish
	 *            Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public void setDateFinish(Date dateFinish) {
		this.dateFinish = new WmDate(dateFinish);
	}

	/**
	 * Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @param dateFinish
	 *            Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public void setDateFinish(String dateFinish) {
		this.dateFinish = new WmDate(dateFinish);
	}

	/**
	 * Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @param dateFinish
	 *            Максимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public void setDateFinish(WmDate dateFinish) {
		this.dateFinish = dateFinish;
	}

	/**
	 * Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @param dateStart
	 *            Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public void setDateStart(Date dateStart) {
		this.dateStart = new WmDate(dateStart);
	}

	/**
	 * Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @param dateStart
	 *            Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = new WmDate(dateStart);
	}

	/**
	 * Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @param dateStart
	 *            Минимальное время и дата создания счета (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public void setDateStart(WmDate dateStart) {
		this.dateStart = dateStart;
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
	 * Номер кошелька для оплаты на который выписывался счет.
	 * 
	 * @param purse
	 *            Номер кошелька для оплаты на который выписывался счет.
	 */
	public void setPurse(PurseNumber purse) {
		this.purse = purse;
	}

	/**
	 * Номер кошелька для оплаты на который выписывался счет.
	 * 
	 * @param purse
	 *            Номер кошелька для оплаты на который выписывался счет.
	 */
	public void setPurse(String purse) {
		this.purse = new PurseNumber(purse);
	}

	/**
	 * Номер счета в системе WebMoney.
	 * 
	 * @param wmInvId
	 *            Номер счета в системе WebMoney.
	 */
	public void setWmInvId(long wmInvId) {
		this.wmInvId = wmInvId;
	}

	/**
	 * Номер счета в системе WebMoney.
	 * 
	 * @param wmInvId
	 *            Номер счета в системе WebMoney.
	 */
	public void setWmInvId(String wmInvId) {
		this.wmInvId = Long.parseLong(wmInvId);
	}

}
