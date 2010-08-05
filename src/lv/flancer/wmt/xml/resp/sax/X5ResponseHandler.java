/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.dict.OperationType;
import lv.flancer.wmt.xml.resp.X5Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X5: Завершение операции с протекцией сделки. Ввод кода протекции.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X5ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	X5Response response;

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X5Response getResponse() {
		return this.response;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих для большинства запросов элементов
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
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X5Response();
		}
		// создаем список операций
		if (qName.equals("operation")) {
			this.response.setId(attributes.getValue("id"));
			this.response.setTs(attributes.getValue("ts"));
		}
	}

}
