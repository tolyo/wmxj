/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X7Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * X7. Проверка АСП клиента - владельца WM Keeper Classic.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X7ResponseHandler extends DefaultHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X7Response response;
	/**
	 * Значение текущего разобранного элемента xml-документа.
	 */
	private String parsedValue;

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
		if (qName.equals("cwmid")) {
			this.response.setCwmid(this.parsedValue);
			return;
		}
		// разбор подмножества элемента "w3s.response/testsign"
		if (qName.equals("res")) {
			this.response.setRes(this.parsedValue.equals("yes"));
			return;
		}
	}

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public X7Response getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X7Response();
		}
	}
}
