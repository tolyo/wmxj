/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X1Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X1: Выписывание счета от одного участника (магазина, ресурса)
 * другому участнику (покупателю).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X1ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X1Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов в "w3s.response"
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "w3s.response/invoice"
		if (qName.equals("orderid")) {
			this.response.setOrderId(this.parsedValue);
			return;
		}
		if (qName.equals("customerwmid")) {
			this.response.setCustomerWmid(this.parsedValue);
			return;
		}
		if (qName.equals("storepurse")) {
			this.response.setStorePurse(this.parsedValue);
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
		if (qName.equals("address")) {
			this.response.setAddress(this.parsedValue);
			this.isHtmlEncodedBeingParsed = false;
			return;
		}
		if (qName.equals("period")) {
			this.response.setPeriod(this.parsedValue);
			return;
		}
		if (qName.equals("expiration")) {
			this.response.setExpiration(this.parsedValue);
			return;
		}
		if (qName.equals("state")) {
			this.response.setState(this.parsedValue);
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

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public X1Response getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X1Response();
		}
		// начало разбора "w3s.response/invoice"
		if (qName.equals("invoice")) {
			this.response.setId(attributes.getValue("id"));
			this.response.setTs(attributes.getValue("ts"));
		} // начало разбора HTML encoded деталей платежа
		if (qName.equals("desc")) {
			this.isHtmlEncodedBeingParsed = true;
			this.parsedValue = "";
		}
		if (qName.equals("address")) {
			this.isHtmlEncodedBeingParsed = true;
			this.parsedValue = "";
		}
	}

}
