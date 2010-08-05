/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X18Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * X18: Получение деталей операции через WM Merchant.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X18ResponseHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	X18Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих для большинства запросов элементов
		super.endElement(uri, localName, qName);
		// разбор подмножества элемента "merchant.response/operation"
		if (qName.equals("amount")) {
			this.response.setAmount(this.parsedValue);
			return;
		}
		if (qName.equals("operdate")) {
			this.response.setOperDate(this.parsedValue);
			return;
		}
		if (qName.equals("purpose")) {
			this.response.setPurpose(this.parsedValue);
			return;
		}
		if (qName.equals("pursefrom")) {
			this.response.setPurseFrom(this.parsedValue);
			return;
		}
		if (qName.equals("wmidfrom")) {
			this.response.setWmidFrom(this.parsedValue);
			return;
		}
		if (qName.equals("capitallerflag")) {
			this.response.setCapitallerFlag(this.parsedValue.equals("1"));
			return;
		}
		if (qName.equals("enumflag")) {
			this.response.setEnumFlag(this.parsedValue.equals("1"));
			return;
		}
		if (qName.equals("IPAddress")) {
			this.response.setIpAddress(this.parsedValue);
			return;
		}
		if (qName.equals("telepat_phone")) {
			this.response.setTelepatPhone(this.parsedValue);
			return;
		}
		if (qName.equals("paymer_number	")) {
			this.response.setPaymerNumber(this.parsedValue);
			return;
		}
		if (qName.equals("paymer_email")) {
			this.response.setPaymerEmail(this.parsedValue);
			return;
		}
		if (qName.equals("cashier_number")) {
			this.response.setCashierNumber(this.parsedValue);
			return;
		}
		if (qName.equals("cashier_date")) {
			this.response.setCashierDate(this.parsedValue);
			return;
		}
		if (qName.equals("cashier_amount")) {
			this.response.setCashierAmount(this.parsedValue);
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X18Response getResponse() {
		return this.response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("merchant.response")) {
			this.response = new X18Response();
		}
		if (qName.equals("operation")) {
			this.response.setWmTransId(attributes.getValue("wmtransid"));
			this.response.setWmInvoiceId(attributes.getValue("wminvoiceid"));
		}
		// начало разбора HTML encoded деталей платежа
		if (qName.equals("purpose")) {
			this.isHtmlEncodedBeingParsed = true;
			this.parsedValue = "";
		}
	}

}
