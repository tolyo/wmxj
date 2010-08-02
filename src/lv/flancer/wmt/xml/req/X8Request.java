/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X8: Получение информации о принадлежности кошелька. Поиск участника
 * системы по его идентификатору или кошельку.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X8Request extends AbstractRequest {
	/**
	 * Номер проверяемого кошелька.
	 */
	private PurseNumber purse;
	/**
	 * Проверяемый WM-идентификатор.
	 */
	private Wmid wmid;

	/**
	 * Номер проверяемого кошелька.
	 * 
	 * @return Номер проверяемого кошелька.
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
		result += this.wmid.getValue();
		result += this.purse.getValue();
		return result;
	}

	/**
	 * Проверяемый WM-идентификатор.
	 * 
	 * @return Проверяемый WM-идентификатор.
	 */
	public Wmid getWmid() {
		return wmid;
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
		// reqn
		result += "<reqn>" + this.requestNum + "</reqn>";
		// signer wmid
		if (this.signerWmid != null) {
			result += "<wmid>" + this.signerWmid + "</wmid>";
		} else {
			result += "<wmid />";
		}
		// sign
		if (this.sign != null) {
			result += "<sign>" + this.sign + "</sign>";
		} else {
			result += "<sign />";
		}
		// trans
		result += "<testwmpurse>";
		result += "<wmid>" + this.getWmid() + "</wmid>";
		result += "<purse>" + this.getPurse() + "</purse>";
		// close tags
		result += "</testwmpurse>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Номер проверяемого кошелька.
	 * 
	 * @param purse
	 *            Номер проверяемого кошелька.
	 */
	public void setPurse(PurseNumber purse) {
		this.purse = purse;
	}

	/**
	 * Номер проверяемого кошелька.
	 * 
	 * @param purse
	 *            Номер проверяемого кошелька.
	 */
	public void setPurse(String purse) {
		this.purse = new PurseNumber(purse);
	}

	/**
	 * Проверяемый WM-идентификатор.
	 * 
	 * @param wmid
	 *            Проверяемый WM-идентификатор.
	 */
	public void setWmid(String wmid) {
		this.wmid = new Wmid(wmid);
	}

	/**
	 * Проверяемый WM-идентификатор.
	 * 
	 * @param wmid
	 *            Проверяемый WM-идентификатор.
	 */
	public void setWmid(Wmid wmid) {
		this.wmid = wmid;
	}

}
