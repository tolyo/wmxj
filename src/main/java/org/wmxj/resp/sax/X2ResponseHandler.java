/**
 * 
 */
package org.wmxj.resp.sax;

import org.wmxj.dict.OperationType;
import org.wmxj.resp.X2Response;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X2: Перевод средств с одного кошелька на другой.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X2ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X2Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов в "w3s.response"
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "w3s.response/operation"
		if (qName.equals("tranid")) {
			this.response.setTranId(this.parsedValue);
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
		if (qName.equals("opertype")) {
			this.response.setOperType(OperationType
					.getByValue(this.parsedValue));
			return;
		}
		if (qName.equals("period")) {
			this.response.setPeriod(this.parsedValue);
			return;
		}
		if (qName.equals("wminvid")) {
			this.response.setWmInvId(this.parsedValue);
			return;
		}
		if (qName.equals("orderid")) {
			this.response.setOrderId(this.parsedValue);
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

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public X2Response getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X2Response();
		}
		// задаем Id операции
		if (qName.equals("operation")) {
			this.response.setId(attributes.getValue("id"));
			//this.response.setTs(attributes.getValue("ts"));
			//аттрибут ts может отсутствовать
			this.response.setTs((attributes.getValue("ts") == null) ? "0" : attributes.getValue("ts"));
		}
		// начало разбора HTML encoded деталей платежа
		if (qName.equals("desc")) {	
			this.isHtmlEncodedBeingParsed = true;
			this.parsedValue = "";
		}
	}

}
