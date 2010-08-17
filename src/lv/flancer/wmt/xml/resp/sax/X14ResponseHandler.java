/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X14Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X14: Бескомиссионный возврат средств отправителю (покупателю).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X14ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X14Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов в "w3s.response"
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "w3s.response/operation"
		if (qName.equals("inwmtranid")) {
			this.response.setInWmTranId(this.parsedValue);
			return;
		}
		if (qName.equals("pursesrc")) {
			this.response.setPurseSrc(this.parsedValue);
			return;
		}
		if (qName.equals("pursedest")) {
			this.response.setPurseDest(this.parsedValue);
			return;
		}
		if (qName.equals("amount")) {
			this.response.setAmount(this.parsedValue);
			return;
		}
		if (qName.equals("comiss")) {
			this.response.setComiss(this.parsedValue);
			return;
		}
		if (qName.equals("desc")) {
			this.response.setDesc(this.parsedValue);
			this.isHtmlEncodedBeingParsed = false;
			return;
		}
		if (qName.equals("datecrt")) {
			this.response.setDateCrt(this.parsedValue);
			return;
		}
		if (qName.equals("dateupd")) {
			this.response.setDateUpd(this.parsedValue);
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X14Response getResponse() {
		return this.response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X14Response();
		}
		// задаем Id операции
		if (qName.equals("operation")) {
			this.response.setId(attributes.getValue("id"));
			this.response.setTs(attributes.getValue("ts"));
		}
		// начало разбора HTML encoded деталей платежа
		if (qName.equals("desc")) {
			this.isHtmlEncodedBeingParsed = true;
			this.parsedValue = "";
		}
	}

}
