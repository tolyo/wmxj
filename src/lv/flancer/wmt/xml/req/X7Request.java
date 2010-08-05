/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X7: Проверка АСП клиента - владельца WM Keeper Classic.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X7Request extends AbstractRequest {
	/**
	 * Строка, которую должен был подписать клиент.
	 */
	private String testSignPlan;
	/**
	 * Подпись строки, передаваемой в параметре testsign\plan, сформированная
	 * клиентом, которого необходимо аутентифицировать.
	 */
	private String testSignSign;
	/**
	 * WM-идентификатор клиента, которого необходимо аутентифицировать.
	 */
	private Wmid testSignWmid;

	/**
	 * Строка, которую должен был подписать клиент.
	 * 
	 * @return Строка, которую должен был подписать клиент.
	 */
	public String getTestSignPlan() {
		return testSignPlan;
	}

	/**
	 * Подпись строки, передаваемой в параметре testsign\plan, сформированная
	 * клиентом, которого необходимо аутентифицировать.
	 * 
	 * @return Подпись строки, передаваемой в параметре testsign\plan,
	 *         сформированная клиентом, которого необходимо аутентифицировать.
	 */
	public String getTestSignSign() {
		return testSignSign;
	}

	/**
	 * WM-идентификатор клиента, которого необходимо аутентифицировать.
	 * 
	 * @return WM-идентификатор клиента, которого необходимо аутентифицировать.
	 */
	public Wmid getTestSignWmid() {
		return testSignWmid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = this.signerWmid.toString();
		result += this.testSignWmid.toString();
		result += this.testSignPlan;
		result += this.testSignSign;
		return result;
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
		result += "<testsign>";
		result += "<wmid>" + this.testSignWmid.toString() + "</wmid>";
		result += "<plan>" + this.testSignPlan + "</plan>";
		result += "<sign>" + this.testSignSign + "</sign>";
		result += "</testsign>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Строка, которую должен был подписать клиент.
	 * 
	 * @param testSignPlan
	 *            Строка, которую должен был подписать клиент.
	 */
	public void setTestSignPlan(String testSignPlan) {
		this.testSignPlan = testSignPlan;
	}

	/**
	 * Подпись строки, передаваемой в параметре testsign\plan, сформированная
	 * клиентом, которого необходимо аутентифицировать.
	 * 
	 * @param testSignSign
	 *            Подпись строки, передаваемой в параметре testsign\plan,
	 *            сформированная клиентом, которого необходимо
	 *            аутентифицировать.
	 */
	public void setTestSignSign(String testSignSign) {
		this.testSignSign = testSignSign;
	}

	/**
	 * WM-идентификатор клиента, которого необходимо аутентифицировать.
	 * 
	 * @param testSignWmid
	 *            WM-идентификатор клиента, которого необходимо
	 *            аутентифицировать.
	 */
	public void setTestSignWmid(Wmid testSignWmid) {
		this.testSignWmid = testSignWmid;
	}

}
