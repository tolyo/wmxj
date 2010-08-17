/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Информация о пользователе в терминах интерфейса X19.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X19UserInfo {
	/**
	 * Номер банковского счета.
	 */
	private String bankAccount;
	/**
	 * Название банка.
	 */
	private String bankName;
	/**
	 * Номер банковской карты.
	 */
	private String cardNumber;
	/**
	 * ID пользователя в платежной системе.
	 */
	private String emoneyId;
	/**
	 * <p>
	 * Название платежной системы.
	 * </p>
	 * <p>
	 * обязательный для operation=5
	 * </p>
	 * <ul>
	 * <li>emoney_name=rbkmoney.ru для RBK Money</li>
	 * <li>emoney_name=paypal.com для PayPal</li>
	 * <li>emoney_name=moneybookers.com для Moneybookers</li>
	 * <li>emoney_name=qiwi.ru для QIWI Кошелёк</li>
	 * <li>emoney_name=money.yandex.ru для Яндекс.Деньги</li>
	 * <li>emoney_name=easypay.by для EasyPay</li>
	 * </ul>
	 */
	private String emoneyName;
	/**
	 * Фамилия пользователя.
	 */
	private String fname;
	/**
	 * Имя пользователя.
	 */
	private String iname;
	/**
	 * Номер мобильного телефона.
	 */
	private String phone;
	/**
	 * Номер паспорта.
	 */
	private String pnomer;
	/**
	 * WMID пользователя.
	 */
	private String wmid;

	/**
	 * Номер банковского счета.
	 * 
	 * @return Номер банковского счета.
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * Название банка.
	 * 
	 * @return Название банка.
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * Номер банковской карты.
	 * 
	 * @return Номер банковской карты.
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * ID пользователя в платежной системе.
	 * 
	 * @return ID пользователя в платежной системе.
	 */
	public String getEmoneyId() {
		return emoneyId;
	}

	/**
	 * <p>
	 * Название платежной системы.
	 * </p>
	 * <p>
	 * обязательный для operation=5
	 * </p>
	 * <ul>
	 * <li>emoney_name=rbkmoney.ru для RBK Money</li>
	 * <li>emoney_name=paypal.com для PayPal</li>
	 * <li>emoney_name=moneybookers.com для Moneybookers</li>
	 * <li>emoney_name=qiwi.ru для QIWI Кошелёк</li>
	 * <li>emoney_name=money.yandex.ru для Яндекс.Деньги</li>
	 * <li>emoney_name=easypay.by для EasyPay</li>
	 * </ul>
	 * 
	 * @return Название платежной системы.
	 */
	public String getEmoneyName() {
		return emoneyName;
	}

	/**
	 * Фамилия пользователя.
	 * 
	 * @return Фамилия пользователя.
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Имя пользователя.
	 * 
	 * @return Имя пользователя.
	 */
	public String getIname() {
		return iname;
	}

	/**
	 * Номер мобильного телефона.
	 * 
	 * @return Номер мобильного телефона.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Номер паспорта.
	 * 
	 * @return Номер паспорта.
	 */
	public String getPnomer() {
		return pnomer;
	}

	/**
	 * WMID пользователя.
	 * 
	 * @return WMID пользователя.
	 */
	public String getWmid() {
		return wmid;
	}

	/**
	 * Номер банковского счета.
	 * 
	 * @param bankAccount
	 *            Номер банковского счета.
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * Название банка.
	 * 
	 * @param bankName
	 *            Название банка.
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * Номер банковской карты.
	 * 
	 * @param cardNumber
	 *            Номер банковской карты.
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * ID пользователя в платежной системе.
	 * 
	 * @param emoneyId
	 *            ID пользователя в платежной системе.
	 */
	public void setEmoneyId(String emoneyId) {
		this.emoneyId = emoneyId;
	}

	/**
	 * <p>
	 * Название платежной системы.
	 * </p>
	 * <p>
	 * обязательный для operation=5
	 * </p>
	 * <ul>
	 * <li>emoney_name=rbkmoney.ru для RBK Money</li>
	 * <li>emoney_name=paypal.com для PayPal</li>
	 * <li>emoney_name=moneybookers.com для Moneybookers</li>
	 * <li>emoney_name=qiwi.ru для QIWI Кошелёк</li>
	 * <li>emoney_name=money.yandex.ru для Яндекс.Деньги</li>
	 * <li>emoney_name=easypay.by для EasyPay</li>
	 * </ul>
	 * 
	 * @param emoneyName
	 *            Название платежной системы.
	 */
	public void setEmoneyName(String emoneyName) {
		this.emoneyName = emoneyName;
	}

	/**
	 * Фамилия пользователя.
	 * 
	 * @param fname
	 *            Фамилия пользователя.
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * Имя пользователя.
	 * 
	 * @param iname
	 *            Имя пользователя.
	 */
	public void setIname(String iname) {
		this.iname = iname;
	}

	/**
	 * Номер мобильного телефона.
	 * 
	 * @param phone
	 *            Номер мобильного телефона.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Номер паспорта.
	 * 
	 * @param pnomer
	 *            Номер паспорта.
	 */
	public void setPnomer(String pnomer) {
		this.pnomer = pnomer;
	}

	/**
	 * WMID пользователя.
	 * 
	 * @param wmid
	 *            WMID пользователя.
	 */
	public void setWmid(String wmid) {
		this.wmid = wmid;
	}
}
