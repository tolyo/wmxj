/**
 * 
 */
package lv.flancer.wmt.xml.req;

/**
 * Интерфейс X13: Возврат незавершенного платежа с протекцией.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X13Request extends AbstractRequest {

	/**
	 * Номер транзакции (целое положительное число) по внутреннему учету
	 * WebMoney Transfer (wmtranid), при этом тип этой транзакции должен быть с
	 * протекцией (по коду или по времени), а состояние транзакции с протекцией
	 * – не завершена.
	 */
	private long wmTranId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.wmTranId;
		result += this.requestNum;
		return result;
	}

	/**
	 * Номер транзакции (целое положительное число) по внутреннему учету
	 * WebMoney Transfer (wmtranid), при этом тип этой транзакции должен быть с
	 * протекцией (по коду или по времени), а состояние транзакции с протекцией
	 * – не завершена.
	 * 
	 * @return Номер транзакции (целое положительное число) по внутреннему учету
	 *         WebMoney Transfer (wmtranid), при этом тип этой транзакции должен
	 *         быть с протекцией (по коду или по времени), а состояние
	 *         транзакции с протекцией – не завершена.
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
		result += "<rejectprotect>";
		result += "<wmtranid>" + this.wmTranId + "</wmtranid>";
		result += "</rejectprotect>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Номер транзакции (целое положительное число) по внутреннему учету
	 * WebMoney Transfer (wmtranid), при этом тип этой транзакции должен быть с
	 * протекцией (по коду или по времени), а состояние транзакции с протекцией
	 * – не завершена.
	 * 
	 * @param wmTranId
	 *            Номер транзакции (целое положительное число) по внутреннему
	 *            учету WebMoney Transfer (wmtranid), при этом тип этой
	 *            транзакции должен быть с протекцией (по коду или по времени),
	 *            а состояние транзакции с протекцией – не завершена.
	 */
	public void setWmTranId(long wmTranId) {
		this.wmTranId = wmTranId;
	}

}
