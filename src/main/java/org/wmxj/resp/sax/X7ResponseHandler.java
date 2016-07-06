/**
 * 
 */
package org.wmxj.resp.sax;

import org.wmxj.resp.X7Response;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X7: Проверка АСП клиента - владельца WM Keeper Classic.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X7ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X7Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих для большинства запросов элементов
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "w3s.response/testsign"
		if (qName.equals("res")) {
			this.response.setRes(this.parsedValue.equals("yes"));
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
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X7Response();
		}
	}
}
