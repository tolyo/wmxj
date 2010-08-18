/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X17Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X17: Операции с арбитражными контрактами.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X17ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X17Response response;

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X17Response getResponse() {
		return this.response;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "passport.response"
		if (qName.equals("contractid")) {
			this.response.setContractId(this.parsedValue);
			return;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("contract.response")) {
			this.response = new X17Response();
		}
	}
}
