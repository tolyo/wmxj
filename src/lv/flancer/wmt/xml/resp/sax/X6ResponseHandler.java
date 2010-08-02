/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.AbstractResponse;
import lv.flancer.wmt.xml.resp.X6Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Интерфейс X6: Отправка сообщения произвольному WM-идентификатору по
 * внутренней почте.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X6ResponseHandler extends DefaultHandler {
	/**
	 * Значение текущего разобранного элемента xml-документа.
	 */
	private String parsedValue;
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X6Response response;

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		this.parsedValue = (new String(ch, start, length)).trim();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор подмножества элемента "w3s.response"
		if (qName.equals("retval")) {
			this.response.setRetVal(this.parsedValue);
			return;
		}
		if (qName.equals("retdesc")) {
			this.response.setRetDesc(this.parsedValue);
			return;
		}
		if (qName.equals("reqn")) {
			this.response.setRequestNum(this.parsedValue);
			return;
		}
		// разбор подмножества элемента "w3s.response/message"
		if (qName.equals("receiverwmid")) {
			this.response.setReceiverWmid(this.parsedValue);
			return;
		}
		if (qName.equals("msgsubj")) {
			this.response.setMsgSubj(this.parsedValue);
			return;
		}
		if (qName.equals("msgtext")) {
			this.response.setMsgText(this.parsedValue);
			return;
		}
		if (qName.equals("datecrt")) {
			this.response.setDateCrt(this.parsedValue);
			return;
		}
	}

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public AbstractResponse getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X6Response();
		}
		// задаем Id сообщения
		if (qName.equals("message")) {
			this.response.setId(attributes.getValue("id"));
		}
	}
}
