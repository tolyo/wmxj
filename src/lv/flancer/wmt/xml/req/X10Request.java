/**
 * 
 */
package lv.flancer.wmt.xml.req;

import java.util.Date;

import lv.flancer.wmt.xml.dict.WmDate;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X10: Получение списка счетов на оплату.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X10Request extends AbstractRequest {
	/**
	 * Максимальное время и дата создания счета.
	 */
	private WmDate dateFinish;
	/**
	 * Минимальное время и дата создания счета.
	 */
	private WmDate dateStart;
	/**
	 * WM-идентификатор, которому был выписан счет(-а) на оплату.
	 */
	private Wmid wmid;
	/**
	 * Номер счета в системе WebMoney.
	 */
	private long wmInvId;

	/**
	 * Максимальное время и дата создания счета.
	 * 
	 * @return Максимальное время и дата создания счета.
	 */
	public WmDate getDateFinish() {
		return dateFinish;
	}

	/**
	 * Минимальное время и дата создания счета.
	 * 
	 * @return Минимальное время и дата создания счета.
	 */
	public WmDate getDateStart() {
		return dateStart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.wmid;
		result += this.wmInvId;
		result += this.dateStart.getWmtDate();
		result += this.dateFinish.getWmtDate();
		result += this.requestNum;
		return result;
	}

	/**
	 * WM-идентификатор, которому был выписан счет(-а) на оплату.
	 * 
	 * @return WM-идентификатор, которому был выписан счет(-а) на оплату.
	 */
	public Wmid getWmid() {
		return wmid;
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
		result += "<getininvoices>";
		result += "<wmid>" + this.wmid + "</wmid>";
		result += "<wminvid>" + this.wmInvId + "</wminvid>";
		result += "<datestart>" + this.dateStart.getWmtDate() + "</datestart>";
		result += "<datefinish>" + this.dateFinish.getWmtDate()
				+ "</datefinish>";
		result += "</getininvoices>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Максимальное время и дата создания счета.
	 * 
	 * @param dateFinish
	 *            Максимальное время и дата создания счета.
	 */
	public void setDateFinish(Date dateFinish) {
		this.dateFinish = new WmDate(dateFinish);
	}

	/**
	 * Максимальное время и дата создания счета.
	 * 
	 * @param dateFinish
	 *            Максимальное время и дата создания счета.
	 */
	public void setDateFinish(String dateFinish) {
		this.dateFinish = new WmDate(dateFinish);
	}

	/**
	 * Максимальное время и дата создания счета.
	 * 
	 * @param dateFinish
	 *            Максимальное время и дата создания счета.
	 */
	public void setDateFinish(WmDate dateFinish) {
		this.dateFinish = dateFinish;
	}

	/**
	 * Минимальное время и дата создания счета.
	 * 
	 * @param dateStart
	 *            Минимальное время и дата создания счета.
	 */
	public void setDateStart(Date dateStart) {
		this.dateStart = new WmDate(dateStart);
	}

	/**
	 * Минимальное время и дата создания счета.
	 * 
	 * @param dateStart
	 *            Минимальное время и дата создания счета.
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = new WmDate(dateStart);
	}

	/**
	 * Минимальное время и дата создания счета.
	 * 
	 * @param dateStart
	 *            Минимальное время и дата создания счета.
	 */
	public void setDateStart(WmDate dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * WM-идентификатор, которому был выписан счет(-а) на оплату.
	 * 
	 * @param wmid
	 *            WM-идентификатор, которому был выписан счет(-а) на оплату.
	 */
	public void setWmid(String wmid) {
		this.wmid = new Wmid(wmid);
	}

	/**
	 * WM-идентификатор, которому был выписан счет(-а) на оплату.
	 * 
	 * @param wmid
	 *            WM-идентификатор, которому был выписан счет(-а) на оплату.
	 */
	public void setWmid(Wmid wmid) {
		this.wmid = wmid;
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
