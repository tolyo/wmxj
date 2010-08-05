/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmDate;

/**
 * Интерфейс X3: Получение истории операций по кошельку. Проверка выполнения
 * операции по переводу средств.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X3Request extends AbstractRequest {
	/**
	 * Максимальное время и дата выполнения операции (ГГГГММДД ЧЧ:ММ:СС).
	 */
	private WmDate dateFinish;
	/**
	 * Минимальное время и дата выполнения операции (ГГГГММДД ЧЧ:ММ:СС).
	 */
	private WmDate dateStart;
	/**
	 * Номер счета в системе учета магазина.Целое число > 0, максимально 2^31-1.
	 */
	private long orderId;
	/**
	 * Номер кошелька для которого запрашивается операция.
	 */
	private PurseNumber purse;
	/**
	 * Номер перевода в системе отправителя. Любое целое число без знака (целое
	 * число > 0, максимально 2^31-1), должно быть уникальным (два перевода с
	 * одним и тем же tranid невозможен).
	 */
	private long tranId;
	/**
	 * Номер счета в системе WebMoney по которому выполнялась операция. Целое
	 * число > 0, максимально 2^31-1.
	 */
	private long wmInvId;
	/**
	 * Номер операции в системе WebMoney. Целое число > 0, максимально 2^31-1.
	 */
	private long wmTranId;

	/**
	 * Максимальное время и дата выполнения операции (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @return Максимальное время и дата выполнения операции (ГГГГММДД
	 *         ЧЧ:ММ:СС).
	 */
	public WmDate getDateFinish() {
		return dateFinish;
	}

	/**
	 * Минимальное время и дата выполнения операции (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @return Минимальное время и дата выполнения операции (ГГГГММДД ЧЧ:ММ:СС).
	 */
	public WmDate getDateStart() {
		return dateStart;
	}

	/**
	 * Номер счета в системе учета магазина.Целое число > 0, максимально 2^31-1.
	 * 
	 * @return Номер счета в системе учета магазина
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Номер кошелька для которого запрашивается операция.
	 * 
	 * @return Номер кошелька для которого запрашивается операция.
	 */
	public PurseNumber getPurse() {
		return purse;
	}

	@Override
	public String getTextToSign() {
		String result = "";
		result += this.purse;
		result += this.requestNum;
		return result;
	}

	/**
	 * Номер перевода в системе отправителя. Любое целое число без знака (целое
	 * число > 0, максимально 2^31-1), должно быть уникальным (два перевода с
	 * одним и тем же tranid невозможен).
	 * 
	 * @return Номер перевода в системе отправителя.
	 */
	public long getTranId() {
		return tranId;
	}

	/**
	 * Номер счета в системе WebMoney по которому выполнялась операция. Целое
	 * число > 0, максимально 2^31-1.
	 * 
	 * @return Номер счета в системе WebMoney по которому выполнялась операция.
	 */
	public long getWmInvId() {
		return wmInvId;
	}

	/**
	 * Номер операции в системе WebMoney. Целое число > 0, максимально 2^31-1.
	 * 
	 * @return Номер операции в системе WebMoney.
	 */
	public long getWmTranId() {
		return wmTranId;
	}

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
		result += "<getoperations>";
		result += "<purse>" + this.purse + "</purse>";
		result += "<wmtranid>" + this.wmTranId + "</wmtranid>";
		result += "<tranid>" + this.tranId + "</tranid>";
		result += "<wminvid>" + this.wmInvId + "</wminvid>";
		result += "<orderid>" + this.orderId + "</orderid>";
		result += "<datestart>" + this.dateStart.getWmtDate() + "</datestart>";
		result += "<datefinish>" + this.dateFinish.getWmtDate()
				+ "</datefinish>";
		result += "</getoperations>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Максимальное время и дата выполнения операции (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @param dateFinish
	 *            Максимальное время и дата выполнения операции (ГГГГММДД
	 *            ЧЧ:ММ:СС).
	 */
	public void setDateFinish(WmDate dateFinish) {
		this.dateFinish = dateFinish;
	}

	/**
	 * Минимальное время и дата выполнения операции (ГГГГММДД ЧЧ:ММ:СС).
	 * 
	 * @param dateStart
	 *            Минимальное время и дата выполнения операции (ГГГГММДД
	 *            ЧЧ:ММ:СС).
	 */
	public void setDateStart(WmDate dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Номер счета в системе учета магазина. Целое число > 0, максимально
	 * 2^31-1.
	 * 
	 * @param orderId
	 *            Номер счета в системе учета магазина
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Номер кошелька для которого запрашивается операция.
	 * 
	 * @param purseNumber
	 *            Номер кошелька для которого запрашивается операция.
	 */
	public void setPurse(PurseNumber purseNumber) {
		this.purse = purseNumber;
	}

	/**
	 * Номер перевода в системе отправителя. Любое целое число без знака (целое
	 * число > 0, максимально 2^31-1), должно быть уникальным (два перевода с
	 * одним и тем же tranid невозможен).
	 * 
	 * @param tranId
	 *            Номер перевода в системе отправителя.
	 */
	public void setTranId(long tranId) {
		this.tranId = tranId;
	}

	/**
	 * Номер счета в системе WebMoney по которому выполнялась операция. Целое
	 * число > 0, максимально 2^31-1.
	 * 
	 * @param wmInvId
	 *            Номер счета в системе WebMoney по которому выполнялась
	 *            операция.
	 */
	public void setWmInvId(long wmInvId) {
		this.wmInvId = wmInvId;
	}

	/**
	 * Номер операции в системе WebMoney. Целое число > 0, максимально 2^31-1.
	 * 
	 * @param wmTranId
	 *            Номер операции в системе WebMoney.
	 */
	public void setWmTranId(long wmTranId) {
		this.wmTranId = wmTranId;
	}

}
