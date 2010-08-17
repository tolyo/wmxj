/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X16Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X16: Создание кошелька.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X16ResponseHandler extends AbstractResponseHandler {

	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X16Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов в "w3s.response"
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "w3s.response/operation"
		if (qName.equals("pursename")) {
			this.response.setPurseName(this.parsedValue);
			return;
		}
		if (qName.equals("amount")) {
			this.response.setAmount(this.parsedValue);
			return;
		}
		if (qName.equals("desc")) {
			this.response.setDesc(this.parsedValue);
			this.isHtmlEncodedBeingParsed = false;
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X16Response getResponse() {
		return this.response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X16Response();
		}
		// задаем Id операции
		if (qName.equals("purse ")) {
			this.response.setId(attributes.getValue("id"));
		}
		// начало разбора HTML encoded деталей платежа
		if (qName.equals("desc")) {
			this.isHtmlEncodedBeingParsed = true;
			this.parsedValue = "";
		}
	}
}
