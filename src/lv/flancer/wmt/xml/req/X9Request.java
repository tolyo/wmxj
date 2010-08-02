/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X9: Получение информации о балансе на кошельках.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X9Request extends AbstractRequest {

	/**
	 * Проверяемый WM-идентификатор.
	 */
	private Wmid wmid;

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.wmid.getValue();
		result += this.getRequestNum();
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
		// getpurses
		result += "<getpurses>";
		result += "<wmid>" + this.getWmid() + "</wmid>";
		// close tags
		result += "</getpurses>";
		result += "</w3s.request>";
		return result;
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
