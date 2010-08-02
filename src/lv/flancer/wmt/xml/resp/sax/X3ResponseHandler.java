/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import java.util.ArrayList;
import java.util.List;

import lv.flancer.wmt.xml.dict.Operation;
import lv.flancer.wmt.xml.dict.OperationType;
import lv.flancer.wmt.xml.resp.X3Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Интерфейс X3: Получение истории операций по кошельку. Проверка выполнения
 * операции по переводу средств.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X3ResponseHandler extends DefaultHandler {
	/**
	 * Флаг, показывающий, что происходит разбор элементов, относящихся к
	 * "w3s.response/operations/operation".
	 */
	private boolean isOperationElementBeingParsed = false;
	/**
	 * Флаг, указывающий, что происходит разбор html-encoded элемента.
	 */
	private boolean isHtmlEncodedBeingParsed = false;
	/**
	 * Отдельная операция.
	 */
	private Operation operation;
	/**
	 * Список платежей, полученных по запросу.
	 */
	private List<Operation> operationList;
	/**
	 * Значение текущего разобранного элемента xml-документа.
	 */
	private String parsedValue;

	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X3Response response;

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
		if (this.isOperationElementBeingParsed) {
			// разбор подмножества элемента "w3s.response/operations/operation"
			this.endElementOperation(qName);
			return;
		} else {
			// разбор подмножества элемента "w3s.response"
			if (qName.equals("reqn")) {
				this.response.setRequestNum(this.parsedValue);
				return;
			}
			if (qName.equals("retval")) {
				this.response.setRetVal(this.parsedValue);
				return;
			}
			if (qName.equals("retdesc")) {
				this.response.setRetDesc(this.parsedValue);
				return;
			}
			// разбор подмножества элемента "w3s.response/operations"
			if (qName.equals("operations")) {
				this.response.setOperationList(this.operationList);
				return;
			}
		}
	}

	/**
	 * Разбор элементов, входящих в подмножество
	 * "w3s.response/operations/operation"
	 * 
	 * @param name
	 */
	private void endElementOperation(String name) {
		// закончили разбор подмножества элемента
		// "w3s.response/operations/operation"
		if (name.equals("operation")) {
			this.isOperationElementBeingParsed = false;
			this.operationList.add(this.operation);
			return;
		}
		// разбор подмножества элемента
		// "w3s.response/operations/operation"
		if (name.equals("pursesrc")) {
			this.operation.setPurseSrc(this.parsedValue);
			return;
		}
		if (name.equals("pursedest")) {
			this.operation.setPurseDest(this.parsedValue);
			return;
		}
		if (name.equals("amount")) {
			this.operation.setAmount(this.parsedValue);
			return;
		}
		if (name.equals("comiss")) {
			this.operation.setComiss(this.parsedValue);
			return;
		}
		if (name.equals("opertype")) {
			this.operation.setOperType(OperationType
					.getByValue(this.parsedValue));
			return;
		}
		if (name.equals("tranid")) {
			this.operation.setTranId(this.parsedValue);
			return;
		}
		if (name.equals("wminvid")) {
			this.operation.setWmInvId(this.parsedValue);
			return;
		}
		if (name.equals("orderid")) {
			this.operation.setOrderId(this.parsedValue);
			return;
		}
		if (name.equals("period")) {
			this.operation.setPeriod(this.parsedValue);
			return;
		}
		if (name.equals("desc")) {
			this.operation.setDesc(this.parsedValue);
			this.isHtmlEncodedBeingParsed = false;
			return;
		}
		if (name.equals("datecrt")) {
			this.operation.setDateCrt(this.parsedValue);
			return;
		}
		if (name.equals("dateupd")) {
			this.operation.setDateUpd(this.parsedValue);
			return;
		}
		if (name.equals("corrwm")) {
			this.operation.setCorrWm(this.parsedValue);
			return;
		}
		if (name.equals("rest")) {
			this.operation.setRest(this.parsedValue);
			return;
		}
	}

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public X3Response getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X3Response();
		}
		// создаем список операций
		if (qName.equals("operations")) {
			this.response.setCount(attributes.getValue("cnt"));
			this.operationList = new ArrayList<Operation>();
		}
		// начало разбора отдельной операции
		if (qName.equals("operation")) {
			this.isOperationElementBeingParsed = true;
			this.operation = new Operation();
			this.operation.setId(attributes.getValue("id"));
			this.operation.setTs(attributes.getValue("ts"));

		}
		// начало разбора HTML encoded деталей платежа
		if (qName.equals("desc")) {
			this.isHtmlEncodedBeingParsed = true;
			this.parsedValue = "";
		}
	}

}
