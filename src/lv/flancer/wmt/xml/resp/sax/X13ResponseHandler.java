/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.dict.OperationType;
import lv.flancer.wmt.xml.resp.X13Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X13: Возврат незавершенного платежа с протекцией.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X13ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X13Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов в "w3s.response"
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "w3s.response/operation"
		if (qName.equals("opertype")) {
			this.response.setOperType(OperationType
					.getByValue(this.parsedValue));
			return;
		}
		if (qName.equals("dateupd")) {
			this.response.setDateUpd(this.parsedValue);
			return;
		}
	}

	@Override
	public X13Response getResponse() {
		return this.response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax. AbstractResponseHandler#getResponse()
	 */

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X13Response();
		}
		// задаем Id операции
		if (qName.equals("operation")) {
			this.response.setId(attributes.getValue("id"));
			this.response.setTs(attributes.getValue("ts"));
		}
	}
}
