/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import java.util.ArrayList;
import java.util.List;

import lv.flancer.wmt.xml.dict.Invoice;
import lv.flancer.wmt.xml.dict.InvoiceState;
import lv.flancer.wmt.xml.resp.X4Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X4: Получение истории выписанных счетов по кошельку. Проверка
 * оплаты счета.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X4ResponseHandler extends AbstractResponseHandler {
	/**
	 * Отдельный счет.
	 */
	private Invoice invoice;
	/**
	 * Список счетов, полученных по запросу.
	 */
	private List<Invoice> invoiceList;
	/**
	 * Флаг, показывающий, что происходит разбор элементов, относящихся к
	 * "w3s.response/outinvoices/outinvoice".
	 */
	private boolean isInvoiceElementBeingParsed = false;
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	X4Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (this.isInvoiceElementBeingParsed) {
			// разбор подмножества элемента
			// "w3s.response/outinvoices/outinvoice"
			this.endElementInvoice(qName);
			return;
		} else {
			// разбор общих для большинства запросов элементов
			super.endElement(uri, localName, qName);
			// разбор подмножества элемента "w3s.response/outinvoices"
			if (qName.equals("outinvoices")) {
				this.response.setInvoiceList(this.invoiceList);
				return;
			}
		}
	}

	/**
	 * Разбор элементов, входящих в подмножество
	 * "w3s.response/outinvoices/outinvoice"
	 * 
	 * @param name
	 */
	private void endElementInvoice(String name) {
		// закончили разбор подмножества элемента
		// "w3s.response/outinvoices/outinvoice"
		if (name.equals("outinvoice")) {
			this.isInvoiceElementBeingParsed = false;
			this.invoiceList.add(this.invoice);
			return;
		}
		// разбор подмножества элемента
		// "w3s.response/outinvoices/outinvoice"
		if (name.equals("orderid")) {
			this.invoice.setOrderId(this.parsedValue);
			return;
		}
		if (name.equals("customerwmid")) {
			this.invoice.setCustomerWmid(this.parsedValue);
			return;
		}
		if (name.equals("storepurse")) {
			this.invoice.setStorePurse(this.parsedValue);
			return;
		}
		if (name.equals("amount")) {
			this.invoice.setAmount(this.parsedValue);
			return;
		}
		if (name.equals("desc")) {
			this.invoice.setDesc(this.decodeCharset(this.parsedValue));
			this.isHtmlEncodedBeingParsed = false;
			return;
		}
		if (name.equals("address")) {
			this.invoice.setAddress(this.decodeCharset(this.parsedValue));
			this.isHtmlEncodedBeingParsed = false;
			return;
		}
		if (name.equals("period")) {
			this.invoice.setPeriod(this.parsedValue);
			return;
		}
		if (name.equals("expiration")) {
			this.invoice.setExpiration(this.parsedValue);
			return;
		}
		if (name.equals("state")) {
			this.invoice.setState(InvoiceState.getByValue(this.parsedValue));
			return;
		}
		if (name.equals("datecrt")) {
			this.invoice.setDateCrt(this.parsedValue);
			return;
		}
		if (name.equals("dateupd")) {
			this.invoice.setDateUpd(this.parsedValue);
			return;
		}
		if (name.equals("wmtranid")) {
			this.invoice.setWmTranid(this.parsedValue);
			return;
		}
	}

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public X4Response getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X4Response();
		}
		// создаем список операций
		if (qName.equals("outinvoices")) {
			this.response.setCount(attributes.getValue("cnt"));
			this.invoiceList = new ArrayList<Invoice>();
		}
		// начало разбора отдельного счета
		if (qName.equals("outinvoice")) {
			this.isInvoiceElementBeingParsed = true;
			this.invoice = new Invoice();
			this.invoice.setId(attributes.getValue("id"));
			this.invoice.setTs(attributes.getValue("ts"));
		}
		// начало разбора HTML encoded деталей платежа
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
