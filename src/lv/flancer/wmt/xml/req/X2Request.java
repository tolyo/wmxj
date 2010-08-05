/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;

/**
 * Интерфейс X2: Перевод средств с одного кошелька на другой.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X2Request extends AbstractRequest {
	/**
	 * Сумма платежа.
	 */
	private WmAmount amount;
	/**
	 * Описание оплачиваемого товара или услуги.
	 */
	private String desc;
	/**
	 * Учитывать разрешение получателя. Если 'false' – перевод будет выполняться
	 * без учета, разрешает ли получатель перевод; 'true' – перевод будет
	 * выполняться только если получатель разрешает перевод (в противном случае
	 * код возврата – 35).
	 */
	private boolean onlyAuth = false;
	/**
	 * Код протекции сделки. Произвольная строка от 0 до 255 символов, пробелы в
	 * начале или конце не допускаются.
	 */
	private String pCode;
	/**
	 * Срок протекции сделки в днях. Целое число от 0 до 255. Если 0 - операция
	 * без протекции сделки.
	 */
	private int period;
	/**
	 * Номер кошелька, на который выполняется перевод (получатель).
	 */
	private PurseNumber purseDest;
	/**
	 * Номер кошелька с которого выполняется перевод (отправитель).
	 */
	private PurseNumber purseSrc;

	/**
	 * Номер перевода в системе учета отправителя.
	 */
	private long tranId;
	/**
	 * Номер счета в системе WebMoney, по которому выполняется перевод.
	 */
	private long wmInvId;

	/**
	 * Сумма платежа.
	 * 
	 * @return Сумма платежа.
	 */
	public WmAmount getAmount() {
		return amount;
	}

	/**
	 * Описание оплачиваемого товара или услуги.
	 * 
	 * @return Описание оплачиваемого товара или услуги.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Код протекции сделки. Произвольная строка от 0 до 255 символов, пробелы в
	 * начале или конце не допускаются.
	 * 
	 * @return Код протекции сделки.
	 */
	public String getPcode() {
		return pCode;
	}

	/**
	 * Срок протекции сделки в днях. Целое число от 0 до 255. Если 0 - операция
	 * без протекции сделки.
	 * 
	 * @return Срок протекции сделки в днях.
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * Номер кошелька, на который выполняется перевод (получатель).
	 * 
	 * @return Номер кошелька, на который выполняется перевод (получатель).
	 */
	public PurseNumber getPurseDest() {
		return purseDest;
	}

	/**
	 * Номер кошелька с которого выполняется перевод (отправитель).
	 * 
	 * @return Номер кошелька с которого выполняется перевод (отправитель).
	 */
	public PurseNumber getPurseSrc() {
		return purseSrc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.requestNum;
		result += this.tranId;
		result += this.purseSrc;
		result += this.purseDest;
		result += this.amount.getWmFormated();
		result += this.period;
		result += this.pCode;
		result += this.desc;
		result += this.wmInvId;
		return result;
	}

	/**
	 * Номер перевода в системе учета отправителя.
	 * 
	 * @return Номер перевода в системе учета отправителя.
	 */
	public long getTranId() {
		return tranId;
	}

	/**
	 * Номер счета в системе WebMoney, по которому выполняется перевод.
	 * 
	 * @return Номер счета в системе WebMoney, по которому выполняется перевод.
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
		result += "<trans>";
		result += "<tranid>" + this.getTranId() + "</tranid>";
		result += "<pursesrc>" + this.getPurseSrc() + "</pursesrc>";
		result += "<pursedest>" + this.getPurseDest() + "</pursedest>";
		result += "<amount>" + this.getAmount().getWmFormated() + "</amount>";
		result += "<period>" + this.getPeriod() + "</period>";
		result += "<pcode>" + this.getPcode() + "</pcode>";
		result += "<desc>" + this.getDesc() + "</desc>";
		result += "<wminvid>" + this.getWmInvId() + "</wminvid>";
		result += "</trans>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Учитывать разрешение получателя. Если 'false' – перевод будет выполняться
	 * без учета, разрешает ли получатель перевод; 'true' – перевод будет
	 * выполняться только если получатель разрешает перевод (в противном случае
	 * код возврата – 35).
	 * 
	 * @return Учитывать разрешение получателя.
	 */
	public boolean isOnlyAuth() {
		return onlyAuth;
	}

	/**
	 * Сумма платежа.
	 * 
	 * @param amount2
	 *            Сумма платежа.
	 */
	public void setAmount(double amount2) {
		this.amount = new WmAmount(amount2);
	}

	/**
	 * Сумма платежа.
	 * 
	 * @param amount
	 *            Сумма платежа.
	 */
	public void setAmount(WmAmount amount) {
		this.amount = amount;
	}

	/**
	 * Описание оплачиваемого товара или услуги.
	 * 
	 * @param desc
	 *            Описание оплачиваемого товара или услуги.
	 */
	public void setDesc(String desc) {
		this.desc = this.prepareString(desc, 255, false);
	}

	/**
	 * Учитывать разрешение получателя. Если 'false' – перевод будет выполняться
	 * без учета, разрешает ли получатель перевод; 'true' – перевод будет
	 * выполняться только если получатель разрешает перевод (в противном случае
	 * код возврата – 35).
	 * 
	 * @param onlyAuth
	 *            Учитывать разрешение получателя.
	 */
	public void setOnlyAuth(boolean onlyAuth) {
		this.onlyAuth = onlyAuth;
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
	 * Срок протекции сделки в днях. Целое число от 0 до 255. Если 0 - операция
	 * без протекции сделки.
	 * 
	 * @param period
	 *            Срок протекции сделки в днях.
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * Срок протекции сделки в днях. Целое число от 0 до 255. Если 0 - операция
	 * без протекции сделки.
	 * 
	 * @param period
	 *            Срок протекции сделки в днях.
	 */
	public void setPeriod(String period) {
		this.period = Integer.parseInt(period);
	}

	/**
	 * Номер кошелька, на который выполняется перевод (получатель).
	 * 
	 * @param purseDest
	 *            Номер кошелька, на который выполняется перевод (получатель).
	 */
	public void setPurseDest(PurseNumber purseDest) {
		this.purseDest = purseDest;
	}

	/**
	 * Номер кошелька, на который выполняется перевод (получатель).
	 * 
	 * @param purseDest
	 *            Номер кошелька, на который выполняется перевод (получатель).
	 */
	public void setPurseDest(String purseDest) {
		this.purseDest = new PurseNumber(purseDest);
	}

	/**
	 * Номер кошелька с которого выполняется перевод (отправитель).
	 * 
	 * @param purseSrc
	 *            Номер кошелька с которого выполняется перевод (отправитель).
	 */
	public void setPurseSrc(PurseNumber purseSrc) {
		this.purseSrc = purseSrc;
	}

	/**
	 * Номер кошелька с которого выполняется перевод (отправитель).
	 * 
	 * @param purseSrc
	 *            Номер кошелька с которого выполняется перевод (отправитель).
	 */
	public void setPurseSrc(String purseSrc) {
		this.purseSrc = new PurseNumber(purseSrc);
	}

	/**
	 * Номер перевода в системе учета отправителя.
	 * 
	 * @param tranId2
	 *            Номер перевода в системе учета отправителя.
	 */
	public void setTranId(long tranId2) {
		this.tranId = tranId2;
	}

	/**
	 * Номер перевода в системе учета отправителя.
	 * 
	 * @param tranId
	 *            Номер перевода в системе учета отправителя.
	 */
	public void setTranId(String tranId) {
		this.tranId = Long.parseLong(tranId);
	}

	/**
	 * Номер счета в системе WebMoney, по которому выполняется перевод.
	 * 
	 * @param wmInvId
	 *            Номер счета в системе WebMoney, по которому выполняется
	 *            перевод.
	 */
	public void setWmInvId(long wmInvId) {
		this.wmInvId = wmInvId;
	}

	/**
	 * Номер счета в системе WebMoney, по которому выполняется перевод.
	 * 
	 * @param wmInvId
	 *            Номер счета в системе WebMoney, по которому выполняется
	 *            перевод.
	 */
	public void setWmInvId(String wmInvId) {
		this.wmInvId = Integer.parseInt(wmInvId);
	}

}
