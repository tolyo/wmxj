/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X6. Отправка сообщения произвольному WM-идентификатору по
 * внутренней почте.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X6Request extends AbstractRequest {
	/**
	 * Тема сообщения. Произвольная строка от 1 до 255 символов. Пробелы в
	 * начале или конце и переводы строк не допускаются.
	 */
	private String msgSubj;
	/**
	 * Текст сообщения. Произвольная строка от 1 до 1024 символов. Пробелы в
	 * начале или конце не допускаются. Для перевода строки используйте '\n'.
	 * 
	 */
	private String msgText;
	/**
	 * WM-идентификатор получателя сообщения.
	 */
	private Wmid receiverWmid;

	/**
	 * Тема сообщения. Произвольная строка от 1 до 255 символов. Пробелы в
	 * начале или конце и переводы строк не допускаются.
	 * 
	 * @return Тема сообщения.
	 */
	public String getMsgSubj() {
		return msgSubj;
	}

	/**
	 * Текст сообщения. Произвольная строка от 1 до 1024 символов. Пробелы в
	 * начале или конце не допускаются. Для перевода строки используйте '\n'.
	 * 
	 * @return Текст сообщения.
	 */
	public String getMsgText() {
		return msgText;
	}

	/**
	 * WM-идентификатор получателя сообщения.
	 * 
	 * @return WM-идентификатор получателя сообщения.
	 */
	public Wmid getReceiverWmid() {
		return receiverWmid;
	}

	@Override
	public String getTextToSign() {
		String result = this.receiverWmid.getValue();
		result += String.valueOf(requestNum);
		result += this.msgText;
		result += this.msgSubj;
		return result;
	}

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
		// message
		result += "<message>";
		// receiver wmid
		result += "<receiverwmid>" + this.receiverWmid + "</receiverwmid>";
		// message subject
		if (this.msgSubj != null) {
			result += "<msgsubj>" + this.msgSubj + "</msgsubj>";
		} else {
			result += "<msgsubj />";
		}
		// message text
		if (this.msgText != null) {
			result += "<msgtext>" + this.msgText + "</msgtext>";
		} else {
			result += "<msgtext />";
		}
		result += "</message>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Тема сообщения. Произвольная строка от 1 до 255 символов. Пробелы в
	 * начале или конце и переводы строк не допускаются.
	 * 
	 * @param msgSubj
	 *            Тема сообщения.
	 */
	public void setMsgSubj(String msgSubj) {
		this.msgSubj = this.prepareString(msgSubj, 255, true);
	}

	/**
	 * Текст сообщения. Произвольная строка от 1 до 1024 символов. Пробелы в
	 * начале или конце не допускаются. Для перевода строки используйте '\n'.
	 * 
	 * @param msgText
	 *            Текст сообщения.
	 */
	public void setMsgText(String msgText) {
		this.msgText = this.prepareString(msgText, 1024, false);
	}

	/**
	 * WM-идентификатор получателя сообщения.
	 * 
	 * @param receiverWmid
	 *            WM-идентификатор получателя сообщения.
	 */
	public void setReceiverWmid(Wmid receiverWmid) {
		this.receiverWmid = receiverWmid;
	}
}
