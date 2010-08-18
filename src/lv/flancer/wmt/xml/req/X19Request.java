/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.X19Operation;
import lv.flancer.wmt.xml.dict.X19UserInfo;

/**
 * Интерфейс X19: Проверка соответствия персональных данных владельца
 * WM-идентификатора.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X19Request extends AbstractRequest {
	/**
	 * Язык запроса. Необязательный параметр: ru – русский язык (значение по
	 * умолчанию), en – английский язык.
	 */
	private String lang;
	/**
	 * Данные о совершенной операции.
	 */
	private X19Operation operation;
	/**
	 * Информация о пользователе.
	 */
	private X19UserInfo userInfo;

	/**
	 * Язык запроса. Необязательный параметр: ru – русский язык (значение по
	 * умолчанию), en – английский язык.
	 * 
	 * @return Язык запроса.
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * Данные о совершенной операции.
	 * 
	 * @return Данные о совершенной операции.
	 */
	public X19Operation getOperation() {
		return operation;
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
		result += this.operation.getType().name().toLowerCase();
		result += this.userInfo.getWmid();
		return result;
	}

	/**
	 * Информация о пользователе.
	 * 
	 * @return Информация о пользователе.
	 */
	public X19UserInfo getUserInfo() {
		return userInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getXmlRequest()
	 */
	@Override
	public String getXmlRequest() {
		String result = "<?xml version=\"1.0\"  encoding=\"windows-1251\"?>";
		result += "<passport.request>";
		result += "<reqn>" + this.requestNum + "</reqn>";
		if (this.lang != null)
			result += "<lang>" + this.lang + "</lang>";
		if (this.signerWmid != null)
			result += "<signerwmid>" + this.signerWmid + "</signerwmid>";
		if (this.sign != null)
			result += "<sign>" + this.sign + "</sign>";
		// operation
		result += "<operation>";
		result += "<type>" + this.operation.getType().name().toLowerCase()
				+ "</type>";
		result += "<direction>"
				+ this.operation.getDirection().name().toLowerCase()
				+ "</direction>";
		result += "<pursetype>" + this.operation.getPurseType()
				+ "</pursetype>";
		result += "<amount>" + this.operation.getAmount().getWmFormated()
				+ "</amount>";
		result += "</operation>";
		// userinfo
		result += "<userinfo>";
		if (this.userInfo.getWmid() != null)
			result += "<wmid>" + this.userInfo.getWmid() + "</wmid>";
		if (this.userInfo.getPnomer() != null)
			result += "<pnomer>" + this.userInfo.getPnomer() + "</pnomer>";
		if (this.userInfo.getFname() != null)
			result += "<fname>" + this.userInfo.getFname() + "</fname>";
		if (this.userInfo.getIname() != null)
			result += "<iname>" + this.userInfo.getIname() + "</iname>";
		if (this.userInfo.getBankName() != null)
			result += "<bank_name>" + this.userInfo.getBankName()
					+ "</bank_name>";
		if (this.userInfo.getBankAccount() != null)
			result += "<bank_account>" + this.userInfo.getBankAccount()
					+ "</bank_account>";
		if (this.userInfo.getCardNumber() != null)
			result += "<card_number>" + this.userInfo.getCardNumber()
					+ "</card_number>";
		if (this.userInfo.getEmoneyName() != null)
			result += "<emoney_name>" + this.userInfo.getEmoneyName()
					+ "</emoney_name>";
		if (this.userInfo.getEmoneyId() != null)
			result += "<emoney_id>" + this.userInfo.getEmoneyId()
					+ "</emoney_id>";
		if (this.userInfo.getPhone() != null)
			result += "<phone>" + this.userInfo.getPhone() + "</phone>";
		result += "</userinfo>";
		result += "</passport.request>";
		return result;
	}

	/**
	 * Язык запроса. Необязательный параметр: ru – русский язык (значение по
	 * умолчанию), en – английский язык.
	 * 
	 * @param lang
	 *            Язык запроса.
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * Данные о совершенной операции.
	 * 
	 * @param operation
	 *            Данные о совершенной операции.
	 */
	public void setOperation(X19Operation operation) {
		this.operation = operation;
	}

	/**
	 * Информация о пользователе.
	 * 
	 * @param userInfo
	 *            Информация о пользователе.
	 */
	public void setUserInfo(X19UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
