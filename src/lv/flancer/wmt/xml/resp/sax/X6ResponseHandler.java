/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X6Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X6: Отправка сообщения произвольному WM-идентификатору по
 * внутренней почте.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X6ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X6Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих для большинства запросов элементов
		super.endElement(uri, localName, qName);
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
	public X6Response getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
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
