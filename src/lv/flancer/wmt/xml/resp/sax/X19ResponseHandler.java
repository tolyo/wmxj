/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X19Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X19: Проверка соответствия персональных данных владельца
 * WM-идентификатора.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X19ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X19Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "passport.response"
		if (qName.equals("retid")) {
			this.response.setRetId(this.parsedValue);
			return;
		}
		if (qName.equals("iname")) {
			this.response.setiName(decodeCharset(this.parsedValue));
			return;
		}
		if (qName.equals("oname")) {
			this.response.setoName(decodeCharset(this.parsedValue));
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X19Response getResponse() {
		return this.response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("passport.response")) {
			this.response = new X19Response();
		}
	}

}
