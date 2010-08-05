/**
 * 
 */
package lv.flancer.wmt.xml.req;

/**
 * Интерфейс X5: Завершение операции с протекцией сделки. Ввод кода протекции.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X5Request extends AbstractRequest {

	/**
	 * Код протекции сделки. Произвольная строка от 0 до 255 символов, пробелы в
	 * начале или конце не допускаются.
	 */
	private String pCode;
	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 */
	private long wmTranId;

	/**
	 * Код протекции сделки. Произвольная строка от 0 до 255 символов, пробелы в
	 * начале или конце не допускаются.
	 * 
	 * @return Код протекции сделки.
	 */
	public String getPcode() {
		return pCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.wmTranId;
		result += this.pCode;
		result += this.requestNum;
		return result;
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @return Уникальный номер платежа в системе учета WebMoney.
	 */
	public long getWmTranId() {
		return wmTranId;
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
		result += "<finishprotect>";
		result += "<wmtranid>" + this.wmTranId + "</wmtranid>";
		result += "<pcode>" + this.pCode + "</pcode>";
		result += "</finishprotect>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Код протекции сделки. Произвольная строка от 0 до 255 символов, пробелы в
	 * начале или конце не допускаются.
	 * 
	 * @param pCode
	 *            Код протекции сделки.
	 */
	public void setPcode(String pCode) {
		this.pCode = this.prepareString(pCode, 255, true);
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @param wmTranid
	 *            Уникальный номер платежа в системе учета WebMoney.
	 */
	public void setWmTranid(String wmTranid) {
		this.wmTranId = Long.parseLong(wmTranid);
	}

	/**
	 * Уникальный номер платежа в системе учета WebMoney.
	 * 
	 * @param wmTranid
	 *            Уникальный номер платежа в системе учета WebMoney.
	 */
	public void setWmTranId(long wmTranid) {
		this.wmTranId = wmTranid;
	}

}
