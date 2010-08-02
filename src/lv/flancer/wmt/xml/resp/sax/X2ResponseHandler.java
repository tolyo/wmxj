/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.dict.OperationType;
import lv.flancer.wmt.xml.resp.X2Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Интерфейс X2: Перевод средств с одного кошелька на другой.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X2ResponseHandler extends DefaultHandler {
	/**
	 * Флаг, указывающий, что происходит разбор html-encoded элемента.
	 */
	private boolean isHtmlEncodedBeingParsed = false;

	/**
	 * Значение текущего разобранного элемента xml-документа.
	 */
	private String parsedValue;

	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X2Response response;

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (this.isHtmlEncodedBeingParsed) {
			this.parsedValue += new String(ch, start, length);
		} else {
			this.parsedValue = (new String(ch, start, length)).trim();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор подмножества элемента "w3s.response"
		if (qName.equals("retval")) {
			this.response.setRetVal(this.parsedValue);
			return;
		}
		if (qName.equals("retdesc")) {
			this.response.setRetDesc(this.parsedValue);
			return;
		}
		if (qName.equals("reqn")) {
			this.response.setRequestNum(this.parsedValue);
			return;
		}
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
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X2Response();
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
