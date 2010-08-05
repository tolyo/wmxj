/**
 * 
 */
package lv.flancer.wmt.xml.req;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.Wmid;
import lv.flancer.wmt.xml.dict.X18AuthType;

/**
 * Интерфейс X18: Получение деталей операции через WM Merchant.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X18Request extends AbstractRequest {
	/**
	 * Тип аутентификации при запросе.
	 */
	private X18AuthType authType;
	/**
	 * Кошелек получателя платежа.
	 */
	private PurseNumber lmiPayeePurse;
	/**
	 * Номер платежа.
	 */
	private long lmiPaymentNo;
	/**
	 * Секретное слово из настроек кошелька lmi_payee_purse в сервисе
	 * merchant.webmoney.ru.
	 */
	private String secretKey;
	/**
	 * Wmid получателя платежа или доверенный WMID.
	 */
	private Wmid wmid;

	/**
	 * Тип аутентификации при запросе.
	 * 
	 * @return Тип аутентификации при запросе.
	 */
	public X18AuthType getAuthType() {
		return authType;
	}

	/**
	 * Кошелек получателя платежа.
	 * 
	 * @return Кошелек получателя платежа.
	 */
	public PurseNumber getLmiPayeePurse() {
		return lmiPayeePurse;
	}

	/**
	 * Номер платежа.
	 * 
	 * @return Номер платежа.
	 */
	public long getLmiPaymentNo() {
		return lmiPaymentNo;
	}

	/**
	 * md5-хэш для подписываемой строки.
	 * 
	 * @return md5-хэш для подписываемой строки.
	 */
	private String getMd5Signature() {
		StringBuffer hexString = new StringBuffer();
		try {
			String text = "" + this.wmid + this.lmiPayeePurse
					+ this.lmiPaymentNo + this.secretKey;
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte[] hash = md.digest();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0"
							+ Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();

	}

	/**
	 * Секретное слово из настроек кошелька lmi_payee_purse в сервисе
	 * merchant.webmoney.ru.
	 * 
	 * @return Секретное слово из настроек кошелька lmi_payee_purse в сервисе
	 *         merchant.webmoney.ru.
	 */
	public String getSecretKey() {
		return secretKey;
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
		result += this.lmiPayeePurse;
		result += this.lmiPaymentNo;
		return result;
	}

	/**
	 * Wmid получателя платежа или доверенный WMID.
	 * 
	 * @return Wmid получателя платежа или доверенный WMID.
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
		result += "<merchant.request>";
		if (this.authType == X18AuthType.WM_SIGNER_AUTH) {
			result += "<wmid>" + this.signerWmid + "</wmid>";
			result += "<lmi_payee_purse>" + this.lmiPayeePurse
					+ "</lmi_payee_purse>";
			result += "<lmi_payment_no>" + this.lmiPaymentNo
					+ "</lmi_payment_no>";
			result += "<sign>" + this.sign + "</sign>";
			result += "<md5 />";
			result += "<secret_key />";
		} else {
			result += "<wmid>" + this.wmid + "</wmid>";
			result += "<lmi_payee_purse>" + this.lmiPayeePurse
					+ "</lmi_payee_purse>";
			result += "<lmi_payment_no>" + this.lmiPaymentNo
					+ "</lmi_payment_no>";
			result += "<sign />";
			if (this.authType == X18AuthType.MD5_AUTH) {
				result += "<md5>" + this.getMd5Signature() + "</md5>";
				result += "<secret_key />";
			} else if (this.authType == X18AuthType.SECRET_KEY_AUTH) {
				result += "<md5 />";
				result += "<secret_key>" + this.secretKey + "</secret_key>";
			}
		}
		result += "</merchant.request>";
		return result;
	}

	/**
	 * Тип аутентификации при запросе.
	 * 
	 * @param authType
	 *            Тип аутентификации при запросе.
	 */
	public void setAuthType(X18AuthType authType) {
		this.authType = authType;
	}

	/**
	 * Кошелек получателя платежа.
	 * 
	 * @param lmiPayeePurse
	 *            Кошелек получателя платежа.
	 */
	public void setLmiPayeePurse(PurseNumber lmiPayeePurse) {
		this.lmiPayeePurse = lmiPayeePurse;
	}

	/**
	 * Кошелек получателя платежа.
	 * 
	 * @param lmiPayeePurse
	 *            Кошелек получателя платежа.
	 */
	public void setLmiPayeePurse(String lmiPayeePurse) {
		this.lmiPayeePurse = new PurseNumber(lmiPayeePurse);
	}

	/**
	 * Номер платежа.
	 * 
	 * @param lmiPaymentNo
	 *            Номер платежа.
	 */
	public void setLmiPaymentNo(long lmiPaymentNo) {
		this.lmiPaymentNo = lmiPaymentNo;
	}

	/**
	 * Номер платежа.
	 * 
	 * @param lmiPaymentNo
	 *            Номер платежа.
	 */
	public void setLmiPaymentNo(String lmiPaymentNo) {
		this.lmiPaymentNo = Long.parseLong(lmiPaymentNo);
	}

	/**
	 * Секретное слово из настроек кошелька lmi_payee_purse в сервисе
	 * merchant.webmoney.ru.
	 * 
	 * @param secretKey
	 *            Секретное слово из настроек кошелька lmi_payee_purse в сервисе
	 *            merchant.webmoney.ru.
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * Wmid получателя платежа или доверенный WMID.
	 * 
	 * @param wmid
	 *            Wmid получателя платежа или доверенный WMID.
	 */
	public void setWmid(String wmid) {
		this.wmid = new Wmid(wmid);
	}

	/**
	 * Wmid получателя платежа или доверенный WMID.
	 * 
	 * @param wmid
	 *            Wmid получателя платежа или доверенный WMID.
	 */
	public void setWmid(Wmid wmid) {
		this.wmid = wmid;
	}

}
