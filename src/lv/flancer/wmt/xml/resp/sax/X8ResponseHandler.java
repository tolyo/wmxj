/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X8Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Интерфейс X8: Получение информации о принадлежности кошелька. Поиск участника
 * системы по его идентификатору или кошельку.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X8ResponseHandler extends DefaultHandler {

	/**
	 * Значение текущего разобранного элемента xml-документа.
	 */
	private String parsedValue;
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X8Response response;

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
		// разбор подмножества элемента "w3s.response/testwmpurse"
		if (qName.equals("wmid")) {
			this.response.setWmid(this.parsedValue);
			return;
		}
		if (qName.equals("purse")) {
			this.response.setPurse(this.parsedValue);
			return;
		}
	}

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public X8Response getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X8Response();
		}
	}

}
